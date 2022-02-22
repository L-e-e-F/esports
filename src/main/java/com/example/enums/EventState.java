package com.example.enums;

public enum EventState {
    EVENT_START("已开赛"),
    EVENT_WAIT("等待开赛"),
    EVENT_JOIN("可参加"),
    EVENT_FAIL("开赛失败");

    final private String state;

    EventState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
