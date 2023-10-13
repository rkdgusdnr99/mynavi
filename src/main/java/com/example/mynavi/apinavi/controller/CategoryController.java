package com.example.mynavi.apinavi.controller;

import com.example.mynavi.api.dto.KakaoApiResponseDto;
import com.example.mynavi.api.service.KakaoCategorySearchService;
import com.example.mynavi.apinavi.service.KakaoRouteSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final KakaoRouteSearchService kakaoRouteSearchService;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    @GetMapping("/attraction")
    public ResponseEntity<KakaoApiResponseDto> getAttraction(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        KakaoApiResponseDto response = kakaoCategorySearchService.requestPharmacyCategorySearch(latitude,longitude,radius);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 적절한 HTTP 상태 코드로 응답
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
