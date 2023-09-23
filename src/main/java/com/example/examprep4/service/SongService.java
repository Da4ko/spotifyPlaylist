package com.example.examprep4.service;

import com.example.examprep4.model.entity.Style;
import com.example.examprep4.model.entity.User;
import com.example.examprep4.model.entity.enums.StyleName;
import com.example.examprep4.model.service.SongServiceModel;
import com.example.examprep4.model.service.UserServiceModel;
import com.example.examprep4.model.view.SongViewModel;

import java.util.List;

public interface SongService {
    void add(SongServiceModel songServiceModel);

    List<SongViewModel> findAllSongsByStyleName(StyleName styleName);

    void addSongToCurrentUserPlayList(String id, User currentUser);

    void removeCurrentUserSongs(User currentUser);
}
