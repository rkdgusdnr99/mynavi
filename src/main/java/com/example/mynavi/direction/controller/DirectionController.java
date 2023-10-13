package com.example.mynavi.direction.controller;

import com.example.mynavi.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
// 네비 컨트롤러
public class DirectionController {

    private final DirectionService mainService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
