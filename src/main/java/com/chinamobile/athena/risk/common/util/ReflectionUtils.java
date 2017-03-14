package com.chinamobile.athena.risk.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
     * ClassName:ReflectionUtils <br/> 
     * Date:     2015年5月3日 下午9:42:50 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class ReflectionUtils {
	
	private static final Log logger = LogFactory.getLog(ReflectionUtils.class);
	
	private static final ConcurrentHashMap<String,Class<?>> typeCache = new ConcurrentHashMap<String,Class<?>>();
	
	public static <T> T newInstance(Class<T> type) throws Exception {
		try {
			Constructor<T> constructor = type.getConstructor();
			return constructor.newInstance();
		} catch (Exception e) {
			throw new Exception("exception occured in method newInstance for type : " + type.toString() ,e);
		} 
	}
	
	public static Class<?> classForName(String name) throws ClassNotFoundException {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		
		Class<?> type = typeCache.get(name);
		type = type != null ? type : Class.forName(name);
		typeCache.putIfAbsent(name, type);
		
		return type;
	}
	
	/**
	 * list get methods if this type
	 * @param type
	 * @return
	 */
	public static List<Method> listGetMethod(Class<?> type) {
		List<Method> methods = new LinkedList<Method>();
		for (Method method : type.getMethods()) {
			if (method.getGenericParameterTypes().length == 0) {
				String methodName = method.getName();
				if (methodName.startsWith("get") && methodName.length() > 3 && !method.isBridge()) {
					methods.add(method);
				} else if (methodName.startsWith("is") && methodName.length() > 2 && !method.isBridge()) {
					methods.add(method);
				}
			}
		}
		
		return methods;
	}
	
	/**
	 * list get methods with specified annotations of this type
	 * @param type
	 * @param annotations
	 * @return
	 */
	public static List<Method> listGetMethodWithAnnotations(Class<?> type, Class<? extends Annotation>... annotations) {
		List<Method> getMethods = listGetMethod(type);
		return filterMethodsWithAnnotations(getMethods, annotations);
	}
	
	/**
	 * list set methods of this  type
	 * @param type
	 * @return
	 */
	public static List<Method> listSetMethod(Class<?> type) {
		List<Method> methods = new LinkedList<Method>();
		for (Method method : type.getMethods()) {
			if (method.getGenericParameterTypes().length == 1) {
				String methodName = method.getName();
				if (methodName.startsWith("set") && methodName.length() > 3 && !method.isBridge()) {
					methods.add(method);
				}
			}
		}
		return methods;
	}
	
	public static List<Method> listSetMethodWithAnnotations(Class<?> type, Class<? extends Annotation>... annotations) {
		List<Method> methods = listSetMethod(type);
		return filterMethodsWithAnnotations(methods, annotations);
	}
	
	private static List<Method> filterMethodsWithAnnotations(List<Method> methods, Class<? extends Annotation>... annotations) {
		
		List<Method> list = new LinkedList<Method>();
		out: 
		for (Iterator<Method> iterator = methods.iterator(); iterator.hasNext();) {
			Method method = iterator.next();
			for (Class<? extends Annotation> item : annotations) {
				if (method.isAnnotationPresent(item)) {
					list.add(method);
					continue out;
				}
			}
		}
		
		return list;
	}
	
	/**
	 * invoke method 
	 * @param method
	 * @param target
	 * @param args
	 * @return
	 */
	public static Object invokeMethod (Method method, Object target,Object... args) {
		try {
			method.setAccessible(true);
			return method.invoke(target, args);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}
		
		return null;
	}
	
	/**
	 * determine this object is collection type or not
	 * @param obj
	 * @return
	 */
	public static boolean isColleciton(Object obj) {
		if (obj instanceof Map || obj instanceof Collection) {
			return true;
		}
		return false;
	}
	
	/**
	 * iterator  the collection, if map, return the values' iterator
	 * @param collecion
	 * @return
	 */
	public static Iterator<?> iteratorColleciton(Object collecion) {
		if (collecion instanceof Map) {
			Map<?,?> map = (Map<?, ?>) collecion;
			return map.values().iterator();
		} else if (collecion instanceof Collection) {
			return ((Collection<?>) collecion).iterator();
		} else {
			return null;
		}
	}
}
