package com.chinamobile.athena.risk.common.conf;

import java.io.InputStream;
import java.net.URL;

/**
 * 
 * <p>文件名称: ConfigurationBase.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月11日 上午6:13:33</p>
 * 
 * <p>版本: version 1.0 </p>
 *
 * <p>输入说明: </p>
 *
 * <p>输出说明: </p>
 *
 * <p>程序流程: </p>
 * 
 * <p>============================================</p>
 * <p>修改序号:</p>
 * <p>时间:	 </p>
 * <p>修改者:  </p>
 * <p>修改内容:  </p>
 * <p>============================================</p>
 */
public abstract class ConfigurationBase implements Configuration {

    /**
     * Get the value of the <code>name</code> property, <code>null</code> if no such property
     * exists.
     * 
     * Values are processed for <a href="#VariableExpansion">variable expansion</a> before being
     * returned.
     * 
     * @param name the property name.
     * @return the value of the <code>name</code> property, or null if no such property exists.
     */
    public abstract String get(String name);

    /**
     * Get the value of the <code>name</code> property. If no such property exists, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value, or <code>defaultValue</code> if the property doesn't exist.
     */
    public String get(String name, String defaultValue) {
        String valueString = get(name);

        if (valueString == null) {
            return defaultValue;
        } else {
            return valueString;
        }
    }

    /**
     * Get the value of the <code>name</code> property as an <code>int</code>.
     * 
     * If no such property exists, or if the specified value is not a valid <code>int</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as an <code>int</code>, or <code>defaultValue</code>.
     */
    public int getInt(String name, int defaultValue) {
        String valueString = get(name);
        if (valueString == null) return defaultValue;
        try {
            String hexString = getHexDigits(valueString);
            if (hexString != null) {
                return Integer.parseInt(hexString, 16);
            }
            return Integer.parseInt(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get the value of the <code>name</code> property as a <code>long</code>. If no such property
     * is specified, or if the specified value is not a valid <code>long</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>long</code>, or <code>defaultValue</code>.
     */
    public long getLong(String name, long defaultValue) {
        String valueString = get(name);
        if (valueString == null) return defaultValue;
        try {
            String hexString = getHexDigits(valueString);
            if (hexString != null) {
                return Long.parseLong(hexString, 16);
            }
            return Long.parseLong(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String getHexDigits(String value) {
        boolean negative = false;
        String str = value;
        String hexString = null;
        if (value.startsWith("-")) {
            negative = true;
            str = value.substring(1);
        }
        if (str.startsWith("0x") || str.startsWith("0X")) {
            hexString = str.substring(2);
            if (negative) {
                hexString = "-" + hexString;
            }
            return hexString;
        }
        return null;
    }

    /**
     * Get the value of the <code>name</code> property as a <code>float</code>. If no such property
     * is specified, or if the specified value is not a valid <code>float</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>float</code>, or <code>defaultValue</code>.
     */
    public float getFloat(String name, float defaultValue) {
        String valueString = get(name);
        if (valueString == null) return defaultValue;
        try {
            return Float.parseFloat(valueString);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get the value of the <code>name</code> property as a <code>boolean</code>. If no such
     * property is specified, or if the specified value is not a valid <code>boolean</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>boolean</code>, or <code>defaultValue</code>.
     */
    public boolean getBoolean(String name, boolean defaultValue) {
        String valueString = get(name);
        if ("true".equals(valueString))
            return true;
        else if ("false".equals(valueString))
            return false;
        else
            return defaultValue;
    }

    /**
     * Get the {@link URL} for the named resource.
     * 
     * @param name resource name.
     * @return the url for the named resource.
     */
    public abstract URL getResource(String name) ;

    /**
     * Get an input stream attached to the configuration resource with the given <code>name</code>.
     * 
     * @param name configuration resource name.
     * @return an input stream attached to the resource.
     */
    public abstract  InputStream getConfResourceAsInputStream(String name) ;

    public abstract void set(String name, String value);

    public void setIfUnset(String name, String value) {
        if ((get(name) == null)) {
            set(name, value);
        }
    }

    public void setInt(String name, int value) {
        set(name, Integer.toString(value));
    }

    public void setLong(String name, long value) {
        set(name, Long.toString(value));
    }

    public void setFloat(String name, float value) {
        set(name, Float.toString(value));
    }

    public void setBoolean(String name, boolean value) {
        set(name, Boolean.toString(value));
    }

}
