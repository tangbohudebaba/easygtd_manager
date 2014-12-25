;(function($) { 
    $.fn.changeTab = function(options) {
       
        var opts = $.extend({},$.fn.changeTab.defaults,options);
        return this.each(function() {
            var obj = $(this);	

            $(obj).find('.activityintro li').on('mouseover', function (){
                obj.find('.activityintro li').removeClass('activityli');
                $(this).addClass('activityli');
                obj.find('.tab_content div').hide();
                obj.find('.tab_content div').eq($(this).index()).show();
            })      
        });  
    }
    $.fn.changeTab.defaults = {
		
    };

})(jQuery);