package com.bryonnicoson.plantpractice.dto;


/**
 * Created by bryon on 2/21/18.
 */

public class TreeDTO extends PlantDTO {

    private int size;
    private String fallColor;

    @Override
    public String toString() {
        return "Tree : " + super.toString() + " " + getSize() + " " + getFallColor();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFallColor() {
        return fallColor;
    }

    public void setFallColor(String fallColor) {
        this.fallColor = fallColor;
    }
}
