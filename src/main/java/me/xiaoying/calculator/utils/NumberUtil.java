package me.xiaoying.calculator.utils;

/**
 * 数字工具库
 */
public class NumberUtil {
    /**
     * 字符串是否是整数
     *
     * @param string 待判断字符串
     * @return Boolean
     */
    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 字符串是否是双浮点数
     *
     * @param string 待判断字符串
     * @return Boolean
     */
    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 字符串是否是单浮点数
     *
     * @param string 待判断字符串
     * @return Boolean
     */
    public static boolean isFloat(String string) {
        try {
            Float.parseFloat(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}