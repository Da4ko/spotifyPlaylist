package com.example.examprep4.model.entity;

import com.example.examprep4.model.entity.enums.StyleName;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{
    private StyleName styleName;
    private String description;

    public Style() {
    }

    public Style(StyleName styleName, String description) {
        this.styleName = styleName;
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    public StyleName getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleName styleName) {
        this.styleName = styleName;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
