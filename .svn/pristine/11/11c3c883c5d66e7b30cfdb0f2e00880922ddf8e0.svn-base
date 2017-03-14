package com.chinamobile.athena.risk.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import com.chinamobile.athena.risk.common.constants.LogCode;
import com.chinamobile.athena.risk.common.entity.ResponseInfo;
import com.chinamobile.athena.risk.common.logging.LogFactory;

public class HttpUtil {

	private static final Logger logger = LogFactory.getLogger(LogCode.RISK_ROUTE);

	public static void toResponse(HttpServletResponse resp, String resJson) {
		logger.info("response the requert message:["+resJson+"]");
		if (resJson != null) {
			resp.setContentType("text/json; charset=utf-8");
			PrintWriter pw;
			try {
				pw = resp.getWriter();
				pw.write(resJson);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				logger.error("response write throws ioexception:"+ e.getMessage());
			}
		}
	}

	public static String getRequertBody(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = readBytes(is, size);
		    String res = new String(reqBodyBytes);
		    logger.info("get RequertBody is:" + res);
			return res;
		} catch (Exception e) {
			logger.warn("get RequertBody throws Exception:" + e.getMessage());
			return null;
		}
	}

	public static final byte[] readBytes(InputStream is, int contentLen) {
		if (contentLen > 0) {
			int readLen = 0;
			int readLengthThisTime = 0;
			byte[] message = new byte[contentLen];
			try {
				while (readLen != contentLen) {
					readLengthThisTime = is.read(message, readLen, contentLen
							- readLen);
					if (readLengthThisTime == -1) {// Should not happen.
						break;
					}
					readLen += readLengthThisTime;
				}
				return message;
			} catch (IOException e) {
				logger.warn("get RequertBody  readBytes throws readBytes:" + e.getMessage());
			}
		}

		return new byte[] {};
	}

	public static void toResponse(HttpServletResponse resp,
			ResponseInfo responseInfo) {
		String resJson = GsonUtils.toJson(responseInfo);
		toResponse(resp, resJson);
	}
}
