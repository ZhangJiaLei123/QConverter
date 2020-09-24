package com.blxt.utils.check;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author MrWang
 * @version v1.0
 * @date 2019/02/20
 * @Description 自定义校验工具类
 */
public class CheckUtils {

    /** demo */
//
//    @Override
//    public Map post(Map<String,String> record) {
//        FieldUtils.stringIsNullCheck(record.get("username"), "用户名不能为空", CODE, "");
//        FieldUtils.stringIsNullCheck(record.get("password"), "密码不能为空", CODE, "");
//        FieldUtils.stringIsNullCheck(record.get("sex"), "性别不能为空", CODE, "");
//        FieldUtils.isNumberCheck(record.get("age"), "输入的年龄不合法", CODE, "");
//        FieldUtils.isIntCheck(record.get("age2"), "请输入整数", CODE, "");
//        FieldUtils.isEmailCheck(record.get("email"), "请输入正确的邮件", CODE, "");
//        FieldUtils.isMobileCheck(record.get("mobile"), "请输入正确的手机号", CODE, "");
//        //dao逻辑操作
//        return record;
//    }

    /**
     * 字符串非空校验
     *
     * @param filed   要校验的字段
     * @param message message
     * @param code    code
     * @param data    data
     */
    public static void stringIsNullCheck(String filed, String message, String code, Object data) {
        if (StrUtil.isBlank(filed)) {
            throw new DataException(code, message, data);
        }
    }

    /**
     * 数字校验
     *
     * @param filed   要校验的字段
     * @param message message
     * @param code    code
     * @param data    data
     */
    public static void isNumberCheck(String filed, String message, String code, Object data) {
        if (!NumberUtil.isNumber(filed)) {
            throw new DataException(code, message, data);
        }
    }

    /**
     * 整数校验
     *
     * @param filed   要校验的字段
     * @param message message
     * @param code    code
     * @param data    data
     */
    public static void isIntCheck(String filed, String message, String code, Object data) {
        if (!NumberUtil.isInteger(filed)) {
            throw new DataException(code, message, data);
        }
    }

    /**
     * 邮件校验
     *
     * @param filed   要校验的字段
     * @param message message
     * @param code    code
     * @param data    data
     */
    public static void isEmailCheck(String filed, String message, String code, Object data) {
        if (!Validator.isEmail(filed)) {
            throw new DataException(code, message, data);
        }
    }

    /**
     * 手机号校验
     *
     * @param filed   要校验的字段
     * @param message message
     * @param code    code
     * @param data    data
     */
    public static void isMobileCheck(String filed, String message, String code, Object data) {
        if (!Validator.isMobile(filed)) {
            throw new DataException(code, message, data);
        }
    }

    /**
     * 对象是否不为空(新增)
     *
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 对象是否为空
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (o.toString().trim().equals("")) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }
}