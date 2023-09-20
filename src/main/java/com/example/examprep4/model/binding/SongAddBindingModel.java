package com.example.examprep4.model.binding;

import com.example.examprep4.model.entity.Style;
import com.example.examprep4.model.entity.enums.StyleName;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class SongAddBindingModel {

    private String Performer;
    private String Title;
    private String duration;
    private LocalDate releaseDate;
    private StyleName style;

    public SongAddBindingModel() {
    }
    @NotBlank(message = "Performer name cannot be empty String")
    @Size(min = 3, max = 20, message = "Performer name must be between 3 and 20 characters" )
    public String getPerformer() {
        return Performer;
    }

    public void setPerformer(String performer) {
        Performer = performer;
    }
    @NotBlank(message = "Title cannot be empty String")
    @Size(min = 2, max = 20, message = "Titl    e must be between 3 and 20 characters" )
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
    @NotBlank(message = "Duration  cannot be empty String")
    @Positive(message = "Duration cannot be negative")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @PastOrPresent
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    @NotNull(message = "You must select style")
    public StyleName getStyle() {
        return style;
    }

    public void setStyle(StyleName style) {
        this.style = style;
    }
}
