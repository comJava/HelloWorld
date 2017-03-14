package com.chinamobile.athena.risk.common.constants;

/**
 * 日志名称枚举类
 * 
 * @ClassName: LogCode
 * @author zing
 * @date 2015-5-27 下午03:02:24
 */
public enum LogCode {



  RISK_ROUTE("RISK_ROUTE_LOG"),
  RISK_TRACK("RISK_TRACK_LOG"),
  RISK_MAIN("RISK_MAIN_LOG"),
  RISK_DATA_RECEIVE("RISK_DATA_RECEIVE_LOG"),
  RISK_DATA_ERROR("RISK_DATA_ERROR_LOG"),
  RISK_TASK("RISK_TASK_LOG");


  private String code;

  private LogCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

}
