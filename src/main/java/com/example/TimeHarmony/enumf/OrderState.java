package com.example.TimeHarmony.enumf;

public enum OrderState {

    NEW(0),
    PENDING(1),
    SHIPPING(2),
    SUCCESS(3),
    FAILED(4),
    DELETED(5);

    private int STATE_VALUE;

    public int getSTATE_VALUE() {
        return STATE_VALUE;
    }

    public void setSTATE_VALUE(int _STATE_VALUE) {
        STATE_VALUE = _STATE_VALUE;
    }

    private OrderState(int value) {
        STATE_VALUE = value;
    }

}
