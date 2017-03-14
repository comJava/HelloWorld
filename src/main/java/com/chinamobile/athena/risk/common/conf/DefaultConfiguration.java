package com.chinamobile.athena.risk.common.conf;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinamobile.athena.risk.common.constants.LogCode;


/**
 * <p>
 * 文件名称: DefaultConfiguration.java
 * </p>
 * <p>
 * 文件功能:
 * </p>
 * <p>
 * 编程者: 王兵
 * </p>
 * <p>
 * 初作时间: 2015年3月11日 上午6:13:41
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
public class DefaultConfiguration extends ConfigurationBase {

    private static final Logger   logger        = LoggerFactory.getLogger(LogCode.RISK_MAIN.getCode());

    protected Map<String, String> propertiesMap = new HashMap<String, String>();

    /*
     * (non-Javadoc)
     * @see cn.vobile.common.conf.ConfigurationBase#get(java.lang.String)
     */
    @Override
    public String get(String name) {
        return propertiesMap.get(name);
    }

    /*
     * (non-Javadoc)
     * @see
     * cn.vobile.common.conf.ConfigurationBase#getResource(java.lang.String)
     */
    @Override
    public URL getResource(String name) {
        return DefaultConfiguration.class.getClassLoader().getResource(name);
    }

    /*
     * (non-Javadoc)
     * @see
     * cn.vobile.common.conf.ConfigurationBase#getConfResourceAsInputStream(
     * java.lang.String)
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

    /*
     * (non-Javadoc)
     * @see cn.vobile.common.conf.ConfigurationBase#set(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void set(String name, String value) {
        synchronized (this) {
            propertiesMap.put(name, value);
        }
    }

}
