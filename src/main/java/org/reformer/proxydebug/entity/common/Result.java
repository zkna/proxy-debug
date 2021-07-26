package org.reformer.proxydebug.entity.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回结果基类， Result.success Result.error
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    //返回code
    private String code;

    //返回信息
    private String msg;

    //返回数据
    private T data;

    //返回成功代码
    public static <T> Result<T> success() {
        return success(null);
    }

    //返回失败代码
    public static <T> Result<T> error() {
        return result(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMsg(), null);
    }

    public static <T> Result<T> success(T data) {
        ResultCode rce = ResultCode.COMMAND_SUCCESS;
        return result(rce, data);
    }

    private static <T> Result<T> result(IResultCode resultCode, T data) {
        return result(resultCode.getCode(), resultCode.getMsg(), data);
    }

    private static <T> Result<T> result(String code, String msg, T data) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
}
