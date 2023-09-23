package com.example.examprep4.web;

import com.example.examprep4.model.entity.enums.StyleName;
import com.example.examprep4.service.SongService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final SongService songService;

    public HomeController(SongService songService) {
        this.songService = songService;
    }




}
