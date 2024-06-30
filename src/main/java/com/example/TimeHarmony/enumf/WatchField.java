package com.example.TimeHarmony.enumf;

public enum WatchField {

    GENDER(1),
    SERIES(2),
    BRAND(3),
    STYLE(4),
    FEATURES(5),
    FAVORITES(6);

    private int FIELD_VALUE;

    public int getFIELD_VALUE() {
        return FIELD_VALUE;
    }

    public void setFIELD_VALUE(int fIELD_VALUE) {
        FIELD_VALUE = fIELD_VALUE;
    }

    private WatchField(int fIELD_VALUE) {
        FIELD_VALUE = fIELD_VALUE;
    }
}
