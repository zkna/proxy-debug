package org.reformer.proxydebug.entity.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements  IResultCode, Serializable {

    COMMAND_SUCCESS("200","成功"),

    ADD_SUCCESS("200","添加成功"),
    DEL_SUCCESS("200","删除成功"),
    UPDATE_SUCCESS("200","更新成功"),
    QUERY_SUCCESS("200","查询成功"),

    ADD_FAIL("500","添加失败"),
    DEL_FAIL("500","删除失败"),
    UPDATE_FAIL("500","更新失败"),
    QUERY_FAIL("500","查询失败"),

    QUERY_NULL("204","没有数据"),

    SYSTEM_EXECUTION_ERROR("500","系统执行出错");

    private String code;

    private String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
