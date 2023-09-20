package com.example.examprep4.model.service;

import com.example.examprep4.model.entity.Style;
import com.example.examprep4.model.entity.enums.StyleName;

import java.time.LocalDate;

public class SongServiceModel {
    private String baseEntity;
    private String Performer;
    private String Title;
    private Integer duration;
    private LocalDate releaseDate;
    private StyleName style;

    public SongServiceModel() {
    }

    public String getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(String baseEntity) {
        this.baseEntity = baseEntity;
    }

    public String getPerformer() {
        return Performer;
    }

    public void setPerformer(String performer) {
        Performer = performer;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }
}
