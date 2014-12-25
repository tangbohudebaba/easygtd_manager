DROP PROCEDURE IF EXISTS gen_next_sequence_pk;
DELIMITER $$

CREATE
    /**
    *	�����һ������ֵ,���������ڴ���һ���¶���
    *	param_1: ��Ӧ����Keyֵ
    *	param_2: ������һ��ֵ
    */
    PROCEDURE gen_next_sequence_pk(IN i_name VARCHAR(128) , OUT o_value BIGINT)
    /*LANGUAGE SQL
    | [NOT] DETERMINISTIC
    | { CONTAINS SQL | NO SQL | READS SQL DATA | MODIFIES SQL DATA }
    | SQL SECURITY { DEFINER | INVOKER }
    | COMMENT 'string'*/
    BEGIN

	DECLARE t_current BIGINT DEFAULT -1;

	INSERT INTO nsc_meap_sequence(id,current,step) VALUES(i_name,100,1) ON DUPLICATE KEY UPDATE current = current + step;

	SELECT current INTO t_current FROM nsc_meap_sequence WHERE id = i_name;	
	
	SET o_value = t_current;

    END$$

DELIMITER ;