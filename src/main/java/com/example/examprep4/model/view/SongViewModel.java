package com.example.examprep4.model.view;

public class SongViewModel {
    private String id;
    private String Performer;
    private String Title;
    private Integer duration;

    public SongViewModel() {
    }

    public String getPerformer() {
        return Performer;
    }

    public void setPerformer(String performer) {
        this.Performer = performer;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
