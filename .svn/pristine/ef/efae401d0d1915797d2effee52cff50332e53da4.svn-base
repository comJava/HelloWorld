package com.chinamobile.athena.risk.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @category 正则工具类
 */
public class RegexUtil {

    /**
     * <pre>
     *  合法E-mail地址：    
     *  必须包含一个并且只有一个符号“@”    
     *  第一个字符不得是“@”或者“.”    
     *  不允许出现“@.”或者.@    
     *  结尾不得是字符“@”或者“.”    
     *  允许“@”前的字符中出现“＋”    
     *  不允许“＋”在最前面，或者“＋@”    
     *  
     * 正则表达式如下：    
     * -----------------------------------------------------------------------    
     * ^(\w+((-\w+)|(\.\w+))*)\+\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$  
     * 
     * ^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$
     * 
     * ^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$
     * -----------------------------------------------------------------------    
     * 
     * 字符描述：    
     * ^ ：匹配输入的开始位置。    
     * \：将下一个字符标记为特殊字符或字面值。    
     * ：匹配前一个字符零次或几次。    
     * + ：匹配前一个字符一次或多次。    
     * (pattern) 与模式匹配并记住匹配。    
     * x|y：匹配 x 或 y。    
     * [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。    
     * \w ：与任何单词字符匹配，包括下划线。    
     * $ ：匹配输入的结尾。
     * </pre>
     */
    public static final String CHECK_MAIL             = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 验证IP地址
     */
    public static final String CHECK_IP               = "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b";

    public static final String CHECK_PHONE            = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 验证字符串是否是数值类型
     */
    public static final String CHECK_STRING           = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$";

    /** 变量命名校验的正则表达式(字母、数字、下划线、中文) */
    public static final String REGULAR_VARIABLE_NAMES = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";

    public static boolean check(String _regex, CharSequence _input) {
        Pattern regex = Pattern.compile(_regex);
        Matcher matcher = regex.matcher(_input);
        boolean isMatched = matcher.matches();
        //System.out.println(isMatched);
        return isMatched;
    }

    /**
     * @param dateTimeStr 需要被验证的字符串
     * @return 如果参数dateTimeStr为yyyy-MM-dd_HH:mm:ss或yyyy-MM-dd
     *         HH:mm:ss的格式，则返回true；否则返回false
     */
    public static boolean isDateTime(String dateTimeStr) {
        return dateTimeStr.matches("\\d{4}-\\d{2}-\\d{2}[_|\\s]\\d{2}:\\d{2}:\\d{2}");
    }

    /**
     * 验证字符串是否为数值
     * 
     * @param num
     */
    public static boolean isNum(String num) {
        return check(CHECK_STRING, num);
    }

}
