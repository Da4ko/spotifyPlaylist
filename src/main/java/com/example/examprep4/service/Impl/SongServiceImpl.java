package com.example.examprep4.service.Impl;

import com.example.examprep4.model.entity.Song;
import com.example.examprep4.model.service.SongServiceModel;
import com.example.examprep4.repository.SongRepository;
import com.example.examprep4.service.SongService;
import com.example.examprep4.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;

    public SongServiceImpl(SongRepository songRepository, ModelMapper modelMapper, StyleService styleService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.styleService = styleService;
    }

    @Override
    public void add(SongServiceModel songServiceModel) {
        Song song = modelMapper.map(songServiceModel, Song.class);
        song.setStyle(styleService.findByName(songServiceModel.getStyle()));
        songRepository.save(song);
    }
}
