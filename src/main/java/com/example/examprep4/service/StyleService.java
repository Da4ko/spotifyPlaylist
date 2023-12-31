package com.example.examprep4.service;

import com.example.examprep4.model.entity.Style;
import com.example.examprep4.model.entity.enums.StyleName;

public interface StyleService {

    void initStyles();
    Style findByName(StyleName styleName);
}
