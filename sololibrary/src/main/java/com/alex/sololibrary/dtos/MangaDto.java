package com.alex.sololibrary.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class MangaDto {
    @NotBlank
    private String title;
    @NotNull
    private int numCap;
    @NotNull
    private String urlImg;
    @NotNull
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

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
