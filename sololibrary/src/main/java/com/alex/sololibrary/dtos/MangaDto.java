package com.alex.sololibrary.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class MangaDto {
    @NotBlank
    private String title;
    @NotNull
    private int numCap;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumCap() {
        return numCap;
    }

    public void setNumCap(int numCap) {
        this.numCap = numCap;
    }
}