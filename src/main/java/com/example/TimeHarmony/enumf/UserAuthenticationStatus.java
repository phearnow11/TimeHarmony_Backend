package com.example.TimeHarmony.enumf;

public enum UserAuthenticationStatus {

    BANNED(0),
    ACTIVE(1);

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private UserAuthenticationStatus(int value) {
        this.value = value;
    }
}
