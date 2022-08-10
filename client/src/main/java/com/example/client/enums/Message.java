package com.example.client.enums;

public enum Message {
    OK(0, "处理成功!"),
    FAIL(500, "处理失败!"),
    ERROR_USER_EXECTION(500, "账号为空"),
    ERROR_PASSWORD_EXECTION(500, "密码为空"),
    ERROR_NOT_REGISTER(500, "尚未注册"),
    ERROR_PASSWORD_ERROR(500, "密码错误"),
    ERROR_PHONE_OCCUPY(500, "手机号被注册"),
    ERROR_VERIFICATION_CODE(500, "验证码错误"),
    ERROR_TOKEN_SIGN(500, "无效签名"),
    ERROR_TOKEN_EXPIRE(500, "token过期"),
    ERROR_TOKEN_ALGORITHMMISMATCH(500, "算法验证失败"),
    ERROR_TOKEN_EXECTION(500, "无效签名"),
    ERROR_DB_EXECTION(500, "数据库异常"),
    ERROR_NOT_VERIFICATION(500, "验证码失效");

    private int code;
    private String message;

    private Message(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
