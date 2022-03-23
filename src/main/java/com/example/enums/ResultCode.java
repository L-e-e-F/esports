package com.example.enums;

public enum ResultCode {
    SUCCESS(0, "请求成功"),
    Unknown_Exception(-1, "未知异常"),
    USER_NOT_FOUND(-1, "没有找到此用户"),
    USER_NOT_LOGIN(-1,"当前没有登录用户"),
    USER_IS_FOUND(-1,"用户已经注册"),
    PASSWORD_FOUND(-1, "密码错误"),
    PASSWORD_FOUND1(-1, "原密码错误"),
    UN_PERMISSION(-1, "没有权限"),
    ERROR_INSERT(-1,"新增失败"),
    ERROR_DELETE(-1,"删除失败"),
    ERROR_UPLOAD(-1,"修改失败"),
    EMPTY_FILE(-1,"未选择文件"),
    EMPTY_MATCHES(-1,"今日无比赛"),
    ERROR_MATCHES(-1,"获取比赛列表失败"),
    ERROR_USER(-1,"获取用户列表失败"),
    ERROR_PLAYER(-1,"获取选手列表失败"),
    ERROR_EVENT(-1,"获取赛事列表失败"),
    ERROR_CLUB(-1,"获取俱乐部列表失败"),
    MENU_EMPTY(-1,"用户菜单获取失败"),
    Unauthorized(-1,"权限不足"),
    ERROR_PASSWORD(-1, "密码重置失败"),
    ERROR_FOLLOW(-1,"关注失败" ),
    TOO_FOLLOW(-1, "已关注"),
    ERROR_UNFOLLOW(-1, "取关失败"),
    NOT_ACQUISITION(-1, "收购失败"),
    NOT_SELL(-1, "卖出失败");



    final private int code;
    final private String message;

    ResultCode(int code, String message) {
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
