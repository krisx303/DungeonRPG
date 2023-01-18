package com.akgroup.project.util;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    * Returns true when both fields have a value less than or equal to the fields of the other object
    * */
    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    /**
     * Returns true when both fields have a value greater than or equal to the fields of the other object
     * */
    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    /**
     * Returns new instance of Vector2D with fields are sum of this and other object
     * */
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    /**
     * Returns new instance of Vector2D with fields are the difference of this and other object
     * */
    Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    /**
     * Returns new instance of Vector2d with each field is maximum of this and other fields
     * */
    Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }


    /**
     * Returns new instance of Vector2d with each field is minimum of this and other fields
     * */
    Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    /**
     * Returns opposite vector2d
     * */
    Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector2d vector2d)) return false;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x +
                "," + y +
                ')';
    }
}
