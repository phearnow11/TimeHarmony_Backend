package com.example.TimeHarmony.enumf;

public enum StaffRole {

    APPRAISER(0),
    SHIPPER(1);

    private int FIELD_VALUE;

    public int getFIELD_VALUE() {
        return FIELD_VALUE;
    }

    public void setFIELD_VALUE(int fIELD_VALUE) {
        FIELD_VALUE = fIELD_VALUE;
    }

    private StaffRole(int fIELD_VALUE) {
        FIELD_VALUE = fIELD_VALUE;
    }
}
