package com.chinamobile.athena.risk.common.conf;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.logging.LogFactory;


/**
 * <p>
 * 文件名称: PropertiesConfiguration.java
 * </p>
 * <p>
 * 文件功能:
 * </p>
 * <p>
 * 编程者: 王兵
 * </p>
 * <p>
 * 初作时间: 2015年3月11日 上午6:13:52
 * </p>
 * <p>
 * 版本: version 1.0
 * </p>
 * <p>
 * 输入说明:
 * </p>
 * <p>
 * 输出说明:
 * </p>
 * <p>
 * 程序流程:
 * </p>
 * <p>=
 * ===========================================
 * </p>
 * <p>
 * 修改序号:
 * </p>
 * <p>
 * 时间:
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改内容:
 * </p>
 * <p>=
 * ===========================================
 * </p>
 */
public class PropertiesConfiguration extends ConfigurationBase {

    private static final Logger logger = LogFactory.getLogger(LogCode.RISK_MAIN);

    private PropertiesConfiguration() {
    }

    private static final Configuration conf = new PropertiesConfiguration();

    /**
     * Stores the mapping of key to the resource which modifies or loads the key
     * most recently
     */
    private static Map<String, String> propertiesMap;

    public static Configuration getInstance() {
        return conf;
    }

    static {
        ApplicationContext propertiesContext = new ClassPathXmlApplicationContext("spring/properties.xml");
        PropertiesLoader propertiesLoader = (PropertiesLoader) propertiesContext.getBean("propertiesLoader");
        propertiesMap = propertiesLoader.getPropertiesMap();
    }

    private ClassLoader classLoader;
    {
        classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null) {
            classLoader = Configuration.class.getClassLoader();
        }
    }

    /**
     * Get the value of the <code>name</code> property, <code>null</code> if no
     * such property exists. Values are processed for <a
     * href="#VariableExpansion">variable expansion</a> before being returned.
     * 
     * @param name the property name.
     * @return the value of the <code>name</code> property, or null if no such
     *         property exists.
     */
    @Override
    public String get(String name) {
        return propertiesMap.get(name);
    }

    /**
     * Get the {@link URL} for the named resource.
     * 
     * @param name resource name.
     * @return the url for the named resource.
     */
    @Override
    public URL getResource(String name) {
        return classLoader.getResource(name);
    }

    /**
     * Get an input stream attached to the configuration resource with the given
     * <code>name</code>.
     * 
     * @param name configuration resource name.
     * @return an input stream attached to the resource.
     */
    @Override
    public InputStream getConfResourceAsInputStream(String name) {
        try {
            URL url = getResource(name);

            if (url == null) {
                logger.info(name + " not found");
                return null;
            } else {
                logger.info("found resource " + name + " at " + url);
            }

            return url.openStream();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void set(String name, String value) {
        propertiesMap.put(name, value);
    }

}
