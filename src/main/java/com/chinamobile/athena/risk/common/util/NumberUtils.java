package com.chinamobile.athena.risk.common.util;

import org.apache.commons.lang.StringUtils;

public class NumberUtils {

	private static final int length = 12;
	
	private NumberUtils() {
	};

	/**
	 * 判断字符串是否是数字类型，如0001,123,123.1均返回true
	 * 
	 * @Title: isNumber
	 * @author: zing
	 * @date 2015-6-19 上午10:11:37
	 * @param str
	 * @return
	 * @return: boolean
	 */
	public static boolean isNumber(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		char[] chars = str.toCharArray();
		int sz = chars.length;
		boolean hasExp = false;
		boolean hasDecPoint = false;
		boolean allowSigns = false;
		boolean foundDigit = false;
		// deal with any possible sign up front
		int start = (chars[0] == '-') ? 1 : 0;
		if (sz > start + 1) {
			if (chars[start] == '0' && chars[start + 1] == 'x') {
				int i = start + 2;
				if (i == sz) {
					return false; // str == "0x"
				}
				// checking hex (it can't be anything else)
				for (; i < chars.length; i++) {
					if ((chars[i] < '0' || chars[i] > '9')
							&& (chars[i] < 'a' || chars[i] > 'f')
							&& (chars[i] < 'A' || chars[i] > 'F')) {
						return false;
					}
				}
				return true;
			}
		}
		sz--; // don't want to loop to the last char, check it afterwords
				// for type qualifiers
		int i = start;
		// loop to the next to last char or to the last char if we need another
		// digit to
		// make a valid number (e.g. chars[0..5] = "1234E")
		while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				foundDigit = true;
				allowSigns = false;

			} else if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				hasDecPoint = true;
			} else if (chars[i] == 'e' || chars[i] == 'E') {
				// we've already taken care of hex.
				if (hasExp) {
					// two E's
					return false;
				}
				if (!foundDigit) {
					return false;
				}
				hasExp = true;
				allowSigns = true;
			} else if (chars[i] == '+' || chars[i] == '-') {
				if (!allowSigns) {
					return false;
				}
				allowSigns = false;
				foundDigit = false; // we need a digit after the E
			} else {
				return false;
			}
			i++;
		}
		if (i < chars.length) {
			if (chars[i] >= '0' && chars[i] <= '9') {
				// no type qualifier, OK
				return true;
			}
			if (chars[i] == 'e' || chars[i] == 'E') {
				// can't have an E at the last byte
				return false;
			}
			if (chars[i] == '.') {
				if (hasDecPoint || hasExp) {
					// two decimal points or dec in exponent
					return false;
				}
				// single trailing decimal point after non-exponent is ok
				return foundDigit;
			}
			if (!allowSigns
					&& (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F')) {
				return foundDigit;
			}
			if (chars[i] == 'l' || chars[i] == 'L') {
				// not allowing L with an exponent
				return foundDigit && !hasExp;
			}
			// last character is illegal
			return false;
		}
		// allowSigns is true iff the val ends in 'E'
		// found digit it to make sure weird stuff like '.' and '1E-' doesn't
		// pass
		return !allowSigns && foundDigit;
	}

	/**
	 * 如果是数字类型，则前补0，使其长度为length
	 * 
	 * @Title: formatBeforeLength
	 * @author: zing
	 * @date 2015-6-19 上午10:26:36
	 * @param str
	 * @param length
	 * @return
	 * @return: String
	 */
	public static String formatBeforeLength(String str, int length) {
		if (!isNumber(str)) {
			return str;
		}
		String[] arr = str.split("\\..");
		if (str.length() >= length) {
			return str;
		}
		str = String.format("%0" + length + "d", Integer.valueOf(arr[0]));
		if (arr.length > 1) {
			str = str + "." + arr[1];
		}
		return str;
	}
	
	/**
	 * 如果是数字类型，则前补0，使其长度为length 12
	 * 
	 * @Title: formatBeforeLength
	 * @author: zing
	 * @date 2015-6-19 上午10:26:36
	 * @param str
	 * @param length
	 * @return
	 * @return: String
	 */
	public static String formatBeforeLength(String str) {
		return formatBeforeLength(str,length);
	}

	/**
	 * 如果是数字类型，去掉字符串前面的0
	 * 
	 * @Title: replaceBeforeZero
	 * @author: zing
	 * @date 2015-6-19 上午10:34:31
	 * @param str
	 * @return
	 * @return: String
	 */
	public static String replaceBeforeZero(String str) {
		if (!isNumber(str)) {
			return str;
		}
		if(str.charAt(0)=='-'){
			return "-"+str.substring(1).replaceAll("^(0+)", "");
		}
		return str.replaceAll("^(0+)", "");
	}
}
