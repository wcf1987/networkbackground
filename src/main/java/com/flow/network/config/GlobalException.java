package com.flow.network.config;

import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalException {

   // private static final Logger log = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ApiResponse handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return ApiResponse.fail(ResponseCode.ACCESS_DENIED.getCode(), ResponseCode.ACCESS_DENIED.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                              HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return ApiResponse.fail(ResponseCode.REQUEST_METHOD_ERROR.getCode(),e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ApiResponse handleServiceException(ServiceException e) {
        //log.error(e.getMessage(), e);
        //Integer code = e.getCode();
        return ApiResponse.fail(ResponseCode.PARAM_ERROR.getCode(),e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return ApiResponse.fail(ResponseCode.INTERNAL_SERVER_ERROR.getCode(),e.getMessage());

    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        //log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return ApiResponse.fail(ResponseCode.INTERNAL_SERVER_ERROR.getCode(),e.getMessage());

    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ApiResponse handleBindException(BindException e) {
        //log.error(e.getMessage(), e);
        String failMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.fail(ResponseCode.PARAM_ERROR.getCode(),failMsg);

    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.fail(ResponseCode.PARAM_ERROR.getCode(),message);
    }

}