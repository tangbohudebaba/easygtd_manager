﻿/**
 * 
 */
package com.nationsky.backstage.core.tool;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.Collection;

import com.nationsky.backstage.util.performance.MemoryUsage;

/**
 * 功能：布隆过滤器
 * 说明：有一定的错报率,但是漏报是不可能的,即容器没有判断成有
 * @author yubaojian0616@163.com
 *
 * mobile enterprise application platform
 * Version 0.1
 */
public class BloomFilter<E> extends MemoryUsage implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BitSet bitset;
    private int bitSetSize;
    private double bitsPerElement;
    private int expectedNumberOfFilterElements; // expected (maximum) number of
                                                // elements to be added
    private int numberOfAddedElements; // number of elements actually added to
                                        // the Bloom filter
    private int k; // number of hash functions

    static final Charset charset = Charset.forName("UTF-8"); // encoding used
                                                                // for storing
                                                                // hash values
                                                                // as strings

    static final String hashName = "MD5"; // MD5 gives good enough accuracy in
                                            // most circumstances. Change to
                                            // SHA1 if it's needed
    static final MessageDigest digestFunction;
    static { // The digest method is reused between instances
        MessageDigest tmp;
        try {
            tmp = java.security.MessageDigest.getInstance(hashName);
        } catch (NoSuchAlgorithmException e) {
            tmp = null;
        }
        digestFunction = tmp;
    }

    /**
     * Constructs an empty Bloom filter. The total length of the Bloom filter
     * will be c*n.
     * 
     * @param c
     *            is the number of bits used per element.
     * @param n
     *            is the expected number of elements the filter will contain.
     * @param k
     *            is the number of hash functions used.
     */
    public BloomFilter(double c, int n, int k) {
        this.expectedNumberOfFilterElements = n;
        this.k = k;
        this.bitsPerElement = c;
        this.bitSetSize = (int) Math.ceil(c * n);
        numberOfAddedElements = 0;
        this.bitset = new BitSet(bitSetSize);
    }

    /**
     * Constructs an empty Bloom filter. The optimal number of hash functions
     * (k) is estimated from the total size of the Bloom and the number of
     * expected elements.
     * 
     * @param bitSetSize
     *            defines how many bits should be used in total for the filter.
     *            定义总数bit的总存储量
     * @param expectedNumberOElements
     *            defines the maximum number of elements the filter is expected to contain.
     *            定义预期最大的element
     * 简单点说:
     * private static BloomFilter<String> bloomFilter = new BloomFilter<String>(2X, X);
     */
    public BloomFilter(int bitSetSize, int expectedNumberOElements) {
        this(bitSetSize / (double) expectedNumberOElements,
                expectedNumberOElements, (int) Math
                        .round((bitSetSize / (double) expectedNumberOElements)
                                * Math.log(2.0)));
    }

    /**
     * @param expectedNumberOElements 定义预期最大的element
     */
    public BloomFilter(int expectedNumberOElements){
    	this(4*expectedNumberOElements,expectedNumberOElements);
    }
    
    /**
     * 默认为10000个对象容器
     */
    public BloomFilter(){
    	this(10000);
    }
    /**
     * Constructs an empty Bloom filter with a given false positive probability.
     * The number of bits per element and the number of hash functions is
     * estimated to match the false positive probability.
     * 
     * @param falsePositiveProbability
     *            is the desired false positive probability.
     * @param expectedNumberOfElements
     *            is the expected number of elements in the Bloom filter.
     */
    public BloomFilter(double falsePositiveProbability,
            int expectedNumberOfElements) {
        this(Math.ceil(-(Math.log(falsePositiveProbability) / Math.log(2)))
                / Math.log(2), // c = k / ln(2)
                expectedNumberOfElements, (int) Math.ceil(-(Math
                        .log(falsePositiveProbability) / Math.log(2)))); // k =
                                                                            // ceil
                                                                            // (
                                                                            // -
                                                                            // log_2
                                                                            // (
                                                                            // false
                                                                            // prob
                                                                            // .
                                                                            // )
                                                                            // )
    }

    /**
     * Construct a new Bloom filter based on existing Bloom filter data.
     * 
     * @param bitSetSize
     *            defines how many bits should be used for the filter.
     * @param expectedNumberOfFilterElements
     *            defines the maximum number of elements the filter is expected
     *            to contain.
     * @param actualNumberOfFilterElements
     *            specifies how many elements have been inserted into the
     *            <code>filterData</code> BitSet.
     * @param filterData
     *            a BitSet representing an existing Bloom filter.
     */
    public BloomFilter(int bitSetSize, int expectedNumberOfFilterElements,
            int actualNumberOfFilterElements, BitSet filterData) {
        this(bitSetSize, expectedNumberOfFilterElements);
        this.bitset = filterData;
        this.numberOfAddedElements = actualNumberOfFilterElements;
    }

    /**
     * Generates a digest based on the contents of a String.
     * 
     * @param val
     *            specifies the input data.
     * @param charset
     *            specifies the encoding of the input data.
     * @return digest as long.
     */
    public static long createHash(String val, Charset charset) {
        return createHash(val.getBytes(charset));
    }

    /**
     * Generates a digest based on the contents of a String.
     * 
     * @param val
     *            specifies the input data. The encoding is expected to be
     *            UTF-8.
     * @return digest as long.
     */
    public static long createHash(String val) {
        return createHash(val, charset);
    }

    /**
     * Generates a digest based on the contents of an array of bytes.
     * 
     * @param data
     *            specifies input data.
     * @return digest as long.
     */
    public static long createHash(byte[] data) {
        long h = 0;
        byte[] res;

        synchronized (digestFunction) {
            res = digestFunction.digest(data);
        }

        for (int i = 0; i < 4; i++) {
            h <<= 8;
            h |= ((int) res[i]) & 0xFF;
        }
        return h;
    }

    /**
     * Compares the contents of two instances to see if they are equal.
     * 
     * @param obj
     *            is the object to compare to.
     * @return True if the contents of the objects are equal.
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BloomFilter<E> other = (BloomFilter<E>) obj;
        if (this.expectedNumberOfFilterElements != other.expectedNumberOfFilterElements) {
            return false;
        }
        if (this.k != other.k) {
            return false;
        }
        if (this.bitSetSize != other.bitSetSize) {
            return false;
        }
        if (this.bitset != other.bitset
                && (this.bitset == null || !this.bitset.equals(other.bitset))) {
            return false;
        }
        return true;
    }

    /**
     * Calculates a hash code for this class.
     * 
     * @return hash code representing the contents of an instance of this class.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.bitset != null ? this.bitset.hashCode() : 0);
        hash = 61 * hash + this.expectedNumberOfFilterElements;
        hash = 61 * hash + this.bitSetSize;
        hash = 61 * hash + this.k;
        return hash;
    }

    /**
     * Calculates the expected probability of false positives based on the
     * number of expected filter elements and the size of the Bloom filter. <br
     * /><br /> The value returned by this method is the <i>expected</i> rate of
     * false positives, assuming the number of inserted elements equals the
     * number of expected elements. If the number of elements in the Bloom
     * filter is less than the expected value, the true probability of false
     * positives will be lower.
     * 
     * @return expected probability of false positives.
     */
    public double expectedFalsePositiveProbability() {
        return getFalsePositiveProbability(expectedNumberOfFilterElements);
    }

    /**
     * Calculate the probability of a false positive given the specified number
     * of inserted elements.
     * 
     * @param numberOfElements
     *            number of inserted elements.
     * @return probability of a false positive.
     */
    public double getFalsePositiveProbability(double numberOfElements) {
        // (1 - e^(-k * n / m)) ^ k
        return Math.pow((1 - Math.exp(-k * (double) numberOfElements
                / (double) bitSetSize)), k);

    }

    /**
     * Get the current probability of a false positive. The probability is
     * calculated from the size of the Bloom filter and the current number of
     * elements added to it.
     * 
     * @return probability of false positives.
     */
    public double getFalsePositiveProbability() {
        return getFalsePositiveProbability(numberOfAddedElements);
    }

    /**
     * Returns the value chosen for K.<br /> <br /> K is the optimal number of
     * hash functions based on the size of the Bloom filter and the expected
     * number of inserted elements.
     * 
     * @return optimal k.
     */
    public int getK() {
        return k;
    }

    /**
     * Sets all bits to false in the Bloom filter.
     */
    public void clear() {
        bitset.clear();
        numberOfAddedElements = 0;
    }

    /**
     * Adds an object to the Bloom filter. The output from the object's
     * toString() method is used as input to the hash functions.
     * 
     * @param element
     *            is an element to register in the Bloom filter.
     */
    public void add(E element) {
        long hash;
        String valString = element.toString();
        for (int x = 0; x < k; x++) {
            hash = createHash(valString + Integer.toString(x));
            hash = hash % (long) bitSetSize;
            bitset.set(Math.abs((int) hash), true);
        }
        numberOfAddedElements++;
    }

    /**
     * Adds all elements from a Collection to the Bloom filter.
     * 
     * @param c
     *            Collection of elements.
     */
    public void addAll(Collection<? extends E> c) {
        for (E element : c)
            add(element);
    }

    /**
     * Returns true if the element could have been inserted into the Bloom
     * filter. Use getFalsePositiveProbability() to calculate the probability of
     * this being correct.
     * 
     * @param element
     *            element to check.
     * @return true if the element could have been inserted into the Bloom
     *         filter.
     */
    public boolean contains(E element) {
        long hash;
        String valString = element.toString();
        for (int x = 0; x < k; x++) {
            hash = createHash(valString + Integer.toString(x));
            hash = hash % (long) bitSetSize;
            if (!bitset.get(Math.abs((int) hash)))
                return false;
        }
        return true;
    }

    /**
     * Returns true if all the elements of a Collection could have been inserted
     * into the Bloom filter. Use getFalsePositiveProbability() to calculate the
     * probability of this being correct.
     * 
     * @param c
     *            elements to check.
     * @return true if all the elements in c could have been inserted into the
     *         Bloom filter.
     */
    public boolean containsAll(Collection<? extends E> c) {
        for (E element : c)
            if (!contains(element))
                return false;
        return true;
    }

    /**
     * Read a single bit from the Bloom filter.
     * 
     * @param bit
     *            the bit to read.
     * @return true if the bit is set, false if it is not.
     */
    public boolean getBit(int bit) {
        return bitset.get(bit);
    }

    /**
     * Set a single bit in the Bloom filter.
     * 
     * @param bit
     *            is the bit to set.
     * @param value
     *            If true, the bit is set. If false, the bit is cleared.
     */
    public void setBit(int bit, boolean value) {
        bitset.set(bit, value);
    }

    /**
     * Return the bit set used to store the Bloom filter.
     * 
     * @return bit set representing the Bloom filter.
     */
    public BitSet getBitSet() {
        return bitset;
    }

    /**
     * Returns the number of bits in the Bloom filter. Use count() to retrieve
     * the number of inserted elements.
     * 
     * @return the size of the bitset used by the Bloom filter.
     */
    public int size() {
        return this.bitSetSize;
    }

    /**
     * Returns the number of elements added to the Bloom filter after it was
     * constructed or after clear() was called.
     * 
     * @return number of elements added to the Bloom filter.
     */
    public int count() {
        return this.numberOfAddedElements;
    }

    /**
     * Returns the expected number of elements to be inserted into the filter.
     * This value is the same value as the one passed to the constructor.
     * 
     * @return expected number of elements.
     */
    public int getExpectedNumberOfElements() {
        return expectedNumberOfFilterElements;
    }

    /**
     * Get expected number of bits per element when the Bloom filter is full.
     * This value is set by the constructor when the Bloom filter is created.
     * See also getBitsPerElement().
     * 
     * @return expected number of bits per element.
     */
    public double getExpectedBitsPerElement() {
        return this.bitsPerElement;
    }

    /**
     * Get actual number of bits per element based on the number of elements
     * that have currently been inserted and the length of the Bloom filter. See
     * also getExpectedBitsPerElement().
     * 
     * @return number of bits per element.
     */
    public double getBitsPerElement() {
        return this.bitSetSize / (double) numberOfAddedElements;
    }
    
    /**
     * 当不存在的时候返回true并添加到容器
     * @param element
     * @return
     */
    public boolean addIfNone(E element){
    	if(this.contains(element))
    		return false;
    	this.add(element);
    	return true;
    }

	/**
	 * 判断所用内存大小时候使用
	 */
	protected Object newInstance() {
		// TODO Auto-generated method stub
		return new BloomFilter<String>(this.bitSetSize, this.expectedNumberOfFilterElements);
	}
}
