package com.example.enums;

public enum MatchesResult {
    HOME_VICTORY("主队胜利"),
    VISITING_VICTORY("客队胜利"),
    RESULT_UNKNOWN("未出结果");

    final private String result;

    MatchesResult(String state) {
        this.result = state;
    }

    public String getState() {
        return result;
    }
}
