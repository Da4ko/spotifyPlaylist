package com.example.examprep4.service.Impl;

import com.example.examprep4.model.entity.Style;
import com.example.examprep4.model.entity.enums.StyleName;
import com.example.examprep4.repository.StyleRepository;
import com.example.examprep4.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    @Override
    public void initStyles() {
        if(styleRepository.count() == 0) {
            Arrays.stream(StyleName.values())
                    .forEach(styleName -> {
                        Style style = new Style(styleName,
                                "Description for " + styleName.name());

                        styleRepository.save(style);
                    });
        }
    }
}
