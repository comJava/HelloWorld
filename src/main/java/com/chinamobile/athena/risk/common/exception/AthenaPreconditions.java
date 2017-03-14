package com.chinamobile.athena.risk.common.exception;

import com.chinamobile.athena.risk.common.constants.ErrorCode;
import com.chinamobile.athena.risk.common.util.ErrorManager;

/**
 * 
     * ClassName:AthenaPreconditions <br/> 
     * Date:     2015年5月19日 下午9:16:44 <br/> 
     * @author   wangbing  
     * @email  wangbingyf@chinamobile.com
     * @version   
     * @since    JDK 1.7
     * @see
 */
public final class AthenaPreconditions  {
    
    private static final ErrorManager sm = ErrorManager.getManager(AthenaServiceException.ERROR_PACKAGE);
    
    private AthenaPreconditions() {}
    
    public static void checkCondition(boolean expression,ErrorCode codeMsg) {
      if (!expression) {
        throw new AthenaServiceException(codeMsg.getCode(),sm.getString(codeMsg.getCode()));
      }
    }

    public static void checkCondition(boolean expression,ErrorCode codeMsg,String message) {
        if (!expression) {
          throw new AthenaServiceException(codeMsg.getCode(),message);
        }
      }
    
    public static String getMessage(String code){
        return sm.getString(code);
    }
}
