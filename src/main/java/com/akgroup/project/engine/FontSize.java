package com.akgroup.project.engine;

public enum FontSize {
    BIG_FONT(32, 36),
    SMALL_FONT(18, 21);


    public final int fontSize;
    public final int offset;

    FontSize(int fontSize, int offset) {
        this.fontSize = fontSize;
        this.offset = offset;
    }
}