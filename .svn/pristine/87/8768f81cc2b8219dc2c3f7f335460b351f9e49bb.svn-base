package com.chinamobile.athena.risk.common.exception;

import com.chinamobile.athena.risk.common.constants.ErrorCode;
import com.chinamobile.athena.risk.common.util.ErrorManager;


/**
 * 
     * ClassName:AthenaServiceException <br/> 
     * Date:     2015年5月19日 下午9:16:44 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public class AthenaServiceException extends RuntimeException {
    
	public static final String    ERROR_PACKAGE = "com.chinamobile.gz.hdware.common.exception";

    /**
     * 
     */
    private static final long serialVersionUID = 6787644036168277489L;
    
    private static final ErrorManager sm = ErrorManager.getManager(AthenaServiceException.ERROR_PACKAGE);
    
    private String errorCode;

    public AthenaServiceException(ErrorCode codeMsg) {
    	this(codeMsg.getCode(),sm.getString(codeMsg.getCode()));
    }
    public AthenaServiceException(String errorCode) {
        this(errorCode, null, null);
    }

    public AthenaServiceException(String errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public AthenaServiceException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public AthenaServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
