package com.flow.network.config;

public enum  ResponseCode {
    SUCCESS(200,"操作成功!"),
    FAILURE(201,"操作失败"),
    /**系统相关的错误码：5开头**/
    ERROR(500,"系统异常，请稍后重试"),
    /**参数相关的错误码：1开头**/
    PARAM_ERROR(1000,"参数异常"),

    /**权限相关的错误码：2开头**/
    INVALID_TOKEN(2001,"访问令牌不合法"),
    ACCESS_DENIED(2002,"没有权限访问该资源"),
    USERNAME_OR_PASSWORD_ERROR(2003,"用户名或密码错误"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    REQUEST_METHOD_ERROR(400,"请求的方法不存在!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),

    /** 无法找到资源错误 */
    NOT_FOUNT_RESOURCE(1001,"没有找到相关资源!"),

    /** 确少必要请求参数异常 */
    PARAMETER_MISSING_ERROR(1003,"确少必要请求参数!"),
    /** 确少必要请求参数异常 */
    REQUEST_MISSING_BODY_ERROR(1004,"缺少请求体!"),

    /** 未知错误 */
    SYSTEM_ERROR(9998,"未知的错误!"),
    /** 系统错误 */
    UNKNOWN_ERROR(9999,"未知的错误!");


    private final int code;

    private final String message;

    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
