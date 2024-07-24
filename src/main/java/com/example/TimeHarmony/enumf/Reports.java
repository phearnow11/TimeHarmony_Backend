package com.example.TimeHarmony.enumf;

public enum Reports {

    ORDER_REPORT(0),
    APPRAISER_REPORT(1),
    MEMBER_REPORT(2);

    private int REPORT_VALUE;

    public int getREPORT_VALUE() {
        return REPORT_VALUE;
    }

    public void setREPORT_VALUE(int _REPORT_VALUE) {
        REPORT_VALUE = _REPORT_VALUE;
    }

    private Reports(int value) {
        REPORT_VALUE = value;
    }
}
