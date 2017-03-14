package com.chinamobile.athena.risk.common.conf;

import java.io.InputStream;
import java.net.URL;

/**
 * 
 * <p>文件名称: Configuration.java</p>
 * 
 * <p>文件功能: </p>
 *
 * <p>编程者: 王兵</p>
 * 
 * <p>初作时间: 2015年3月11日 上午6:13:23</p>
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
public interface Configuration {

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
    String get(String name);


    /**
     * Get the value of the <code>name</code> property. If no such property exists, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value, or <code>defaultValue</code> if the property doesn't exist.
     */
    String get(String name, String defaultValue);

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
    int getInt(String name, int defaultValue);


    /**
     * Get the value of the <code>name</code> property as a <code>long</code>. If no such property
     * is specified, or if the specified value is not a valid <code>long</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>long</code>, or <code>defaultValue</code>.
     */
    long getLong(String name, long defaultValue);


    /**
     * Get the value of the <code>name</code> property as a <code>float</code>. If no such property
     * is specified, or if the specified value is not a valid <code>float</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>float</code>, or <code>defaultValue</code>.
     */
    float getFloat(String name, float defaultValue);

    /**
     * Get the value of the <code>name</code> property as a <code>boolean</code>. If no such
     * property is specified, or if the specified value is not a valid <code>boolean</code>, then
     * <code>defaultValue</code> is returned.
     * 
     * @param name property name.
     * @param defaultValue default value.
     * @return property value as a <code>boolean</code>, or <code>defaultValue</code>.
     */
    boolean getBoolean(String name, boolean defaultValue);

    /**
     * Get the {@link URL} for the named resource.
     * 
     * @param name resource name.
     * @return the url for the named resource.
     */
    URL getResource(String name);

    /**
     * Get an input stream attached to the configuration resource with the given <code>name</code>.
     * 
     * @param name configuration resource name.
     * @return an input stream attached to the resource.
     */
    InputStream getConfResourceAsInputStream(String name);


    /**
     * Set the <code>value</code> of the <code>name</code> property.
     * 
     * @param name property name.
     * @param value property value.
     */
    public void set(String name, String value);

    /**
     * Sets a property if it is currently unset.
     * 
     * @param name the property name
     * @param value the new value
     */
    public void setIfUnset(String name, String value);

    /**
     * Set the value of the <code>name</code> property to an <code>int</code>.
     * 
     * @param name property name.
     * @param value <code>int</code> value of the property.
     */
    public void setInt(String name, int value);


    /**
     * Set the value of the <code>name</code> property to a <code>long</code>.
     * 
     * @param name property name.
     * @param value <code>long</code> value of the property.
     */
    public void setLong(String name, long value);

    /**
     * Set the value of the <code>name</code> property to a <code>float</code>.
     * 
     * @param name property name.
     * @param value property value.
     */
    public void setFloat(String name, float value);


    /**
     * Set the value of the <code>name</code> property to a <code>boolean</code>.
     * 
     * @param name property name.
     * @param value <code>boolean</code> value of the property.
     */
    public void setBoolean(String name, boolean value);

}
