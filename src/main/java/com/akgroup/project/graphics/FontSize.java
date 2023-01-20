package com.akgroup.project.graphics;

public enum FontSize {
    BIG_FONT(32, 36),
    SMALL_FONT(18, 18);


    public final int fontSize;
    public final int offset;

    FontSize(int fontSize, int offset) {
        this.fontSize = fontSize;
        this.offset = offset;
    }
}