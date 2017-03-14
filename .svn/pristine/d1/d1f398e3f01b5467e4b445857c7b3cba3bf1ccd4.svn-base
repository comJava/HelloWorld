package com.chinamobile.athena.risk.common.constants;

/**
 * ClassName:ErrorCode <br/>
 * Date: 2015年5月19日 下午9:16:44 <br/>
 * 
 * @author wangbing
 * @email wangbingyf@chinamobile.com
 * @version
 * @since JDK 1.7
 * @see
 */

public enum ErrorCode {

	MSG_TRANS_OBJ_IS_ERROR("100001"),
	MSG_UNKOWN_ERROR("100002"),
    MSG_BODY_IS_NULL("101003"),
    MSG_BOYD_COMMON_IS_NULL("101004"),
    MSG_BODY_EQUIPMENT_IS_NULL("101005"),
    MSG_BODY_LOCATION_IS_NULL("101006"),
    MSG_HEADER_IS_NULL("101007"),
    MSG_HEADER_SYSTIME_IS_NULL("101008"),
    MSG__BODY_COMMON_USERNAME_IS_NULL("101009"),
    MSG__BODY_COMMON_APPID_IS_NULL("101010"),
    MSG__BODY_COMMON_OS_IS_NULL("101011"),
    MSG__BODY_COMMON_TRIGGERTIME_IS_NULL("101012"),
	MSG_RISK_ANYSIS_ERROR("101013"),
	MSG_BODY_EQUIP_CPUID_OR_IRFA_IS_NULL("101014"),
	MSG_RISK_ANYSIS_BY_DROOL_ERROR("101015");
	
    private String code;

    private ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static ErrorCode enumCodeOf(String code) {
        ErrorCode[] values = ErrorCode.values();
        ErrorCode errorCode = null;
        for (ErrorCode value : values) {
            if (value.getCode().equalsIgnoreCase(code)) {
                errorCode = value;
                break;
            }
        }
        return errorCode;
    }

}
