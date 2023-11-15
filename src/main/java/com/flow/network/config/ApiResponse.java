package com.flow.network.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.Serializable;
import java.util.StringJoiner;

@Data
@NoArgsConstructor
@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,getterVisibility = JsonAutoDetect.Visibility.NONE)
public class ApiResponse<T> implements Serializable {


        private Integer code;

        private String message;

        private T data;

        public ApiResponse(Integer code,String message){
            this.code = code;
            this.message = message;
        }

        public ApiResponse(Integer code,String message,T data){
            this(code,message);
            this.data = data;
        }

        //无参，比如说添加用户、编辑用户成功，我们直接返回200，操作成功
        public static <T> ApiResponse<T> success(){
            return new ApiResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage());
        }

    //比如说请求数据，返回一个列表，我们之直接返回200，操作成功，数据
        public static <T> ApiResponse<T> success(T data){
            return new ApiResponse<>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage(),data);
        }

        public static <T> ApiResponse<T> fail(){
            return new ApiResponse<>(ResponseCode.FAILURE.getCode(),ResponseCode.FAILURE.getMessage());
        }

        public static <T> ApiResponse<T> fail(Integer code,String message){
            return new ApiResponse<>(code,message);
        }

        public static <T> ApiResponse<T> error(){
            return new ApiResponse<>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMessage());
        }

        public void setCode(Integer code){
            this.code = code;
        }
        public void setMessage(String message){
            this.message = message;
        }
        public void setData(T data){
            this.data = data;
        }
        public Integer getCode(){
            return this.code;
        }
        public String getMessage(){
            return this.message;
        }
        public T getData(){
            return this.data;
        }

        @Override
        public String toString() {
            return (new StringJoiner(", ", ApiResponse.class.getSimpleName() + "[", "]"))
                    .add("code=" + this.code)
                    .add("message='" + this.message + "'")
                    .add("data=" + this.data)
                    .toString();
        }
    /** -------- 指定异常处理方法 -------- **/
    @ExceptionHandler(NullPointerException.class)
    public ApiResponse error(NullPointerException e) {
        e.printStackTrace();
        return new ApiResponse(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMessage());
    }

    /** -------- 参数校验异常 -------- **/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new ApiResponse(ResponseCode.PARAM_ERROR.getCode(), ResponseCode.PARAM_ERROR.getMessage());

    }
    }


