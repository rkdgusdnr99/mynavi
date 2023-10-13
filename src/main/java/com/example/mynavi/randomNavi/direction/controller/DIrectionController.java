package com.example.mynavi.randomNavi.direction.controller;

import com.example.mynavi.randomNavi.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
// 네비 컨트롤러
public class DIrectionController {

    private final DirectionService mainService;

    @GetMapping("/")
    public String main(){
        return "index";
    }




}
