package com.example.mynavi.randomNavi.apinavi.service;

import com.example.mynavi.randomNavi.api.dto.KakaoApiResponseDto;
import com.example.mynavi.randomNavi.api.service.KakaoUriBuilderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoCategorySearchService { // 특정 카테고리 -> 약국

    private final KakaoUriBuilderService kakaoUriBuilderService;

    private final RestTemplate restTemplate;

    private static final String PARK_CATEGORY = "AT4";

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;


    // KakaoAddressSearch -> 주소 -> 위도,경도 -> 값을 매핑
    public KakaoApiResponseDto requestPharmacyCategorySearch(double y, double x, double radius){

        URI uri = kakaoUriBuilderService.buildUriByCategorySearch(y, x, radius, PARK_CATEGORY);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity httpEntity = new HttpEntity(headers);

        // kakao api 호출
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();

    }

}
