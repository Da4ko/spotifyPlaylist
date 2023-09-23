package com.example.examprep4.service.Impl;

import com.example.examprep4.model.entity.Song;
import com.example.examprep4.model.entity.User;
import com.example.examprep4.model.entity.enums.StyleName;
import com.example.examprep4.model.service.SongServiceModel;
import com.example.examprep4.model.service.UserServiceModel;
import com.example.examprep4.model.view.SongViewModel;
import com.example.examprep4.repository.SongRepository;
import com.example.examprep4.repository.UserRepository;
import com.example.examprep4.service.SongService;
import com.example.examprep4.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final UserRepository userRepository;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService,
                           UserRepository userRepository) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
        this.userRepository = userRepository;
    }

    @Override
    public void add(SongServiceModel songServiceModel) {
        Song song = modelMapper.map(songServiceModel, Song.class);
        song.setStyle(styleService.findByName(songServiceModel.getStyle()));
        songRepository.save(song);
    }

    @Override
    public List<SongViewModel> findAllSongsByStyleName(StyleName styleName) {
        return songRepository.findAllByStyle_StyleName(styleName)
                .stream().map(song -> modelMapper.map(song, SongViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addSongToCurrentUserPlayList(String id, User currentUser) {

        User user = userRepository.findById(currentUser.getId()).orElse(null);

        if(user != null){
            Set<Song> userSongs = user.getPlaylist();
            Song song = songRepository.findById(id).orElse(null);
            if(song != null){
                userSongs.add(song);
            }

            userRepository.save(user);
        }

    }

    @Override
    public void removeCurrentUserSongs(User currentUser) {
        User user = userRepository.findById(currentUser.getId()).orElse(null);
        if(user != null){
            Set<Song> userSongs = user.getPlaylist();
            userSongs.clear();
            /*Song song = songRepository.findById(id).orElse(null);
            if(song != null){
                userSongs.add(song);
            }*/

            userRepository.save(user);
        }
    }
}
