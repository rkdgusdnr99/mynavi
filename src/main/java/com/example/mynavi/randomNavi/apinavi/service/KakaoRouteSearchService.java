package com.example.mynavi.randomNavi.apinavi.service;

import com.example.mynavi.randomNavi.api.dto.DocumentDto;
import com.example.mynavi.randomNavi.api.dto.KakaoApiResponseDto;
import com.example.mynavi.randomNavi.api.service.KakaoAddressSearchService;
import com.example.mynavi.randomNavi.apinavi.dto.KakaoRouteAllResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Slf4j(topic = "KakaoRouteSearchService")
@Service
@RequiredArgsConstructor
public class KakaoRouteSearchService {

    private final RestTemplate restTemplate;
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final KakaoCategorySearchService kakaoCategorySearchService;

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;


    public KakaoRouteAllResponseDto requestRandomWays(String originAddress, Integer redius) {


       if (ObjectUtils.isEmpty(originAddress) || ObjectUtils.isEmpty(redius)) return null;

        // 출발지와 도착지 주소를 각각 좌표로 변환
        DocumentDto origin = kakaoAddressSearchService.requestAddressSearch(originAddress).getDocumentDtoList().get(0);

        /***
         * 목적지와 경유지 값을 반경으로 계산해서 가져오는 메소드
         ***/
        KakaoApiResponseDto responses = kakaoCategorySearchService.requestPharmacyCategorySearch(origin.getY(), origin.getX(), redius);

        /***
         랜덤으로 다중 목적지와 경유지 만들기 알고리즘
         ***/
        int RandomLength = responses.getDocumentDtoList().size();

        Random rd = new Random();

        int destinationCnt = rd.nextInt(RandomLength);
        int waypointsCnt = rd.nextInt(RandomLength);

        if(destinationCnt == waypointsCnt){
            waypointsCnt = destinationCnt - 1;
        }

        DocumentDto destination = responses.getDocumentDtoList().get(destinationCnt);
        DocumentDto waypoints = responses.getDocumentDtoList().get(waypointsCnt);

        /***
         * 요청 헤더 만드는 공식
         ***/

        return makeRequestForm(origin,destination,waypoints);

    }

    public KakaoRouteAllResponseDto requestRamdomWay(String originAddress, String destinationAddress,Integer redius) {


        if (ObjectUtils.isEmpty(originAddress) || ObjectUtils.isEmpty(redius) || ObjectUtils.isEmpty(destinationAddress)) return null;

        // 출발지와 도착지 주소를 각각 좌표로 변환
        DocumentDto origin = kakaoAddressSearchService.requestAddressSearch(originAddress).getDocumentDtoList().get(0);
        DocumentDto destination = kakaoAddressSearchService.requestAddressSearch(destinationAddress).getDocumentDtoList().get(0);

        /***
         * 목적지와 경유지 값을 반경으로 계산해서 가져오는 메소드
         ***/
        KakaoApiResponseDto responses = kakaoCategorySearchService.requestPharmacyCategorySearch(origin.getY(), origin.getX(), redius);

        /***
         랜덤으로 경유지 만들기 알고리즘
         ***/
        int RandomLength = responses.getDocumentDtoList().size();

        Random rd = new Random();

        int waypointsCnt = rd.nextInt(RandomLength);

        DocumentDto waypoints = responses.getDocumentDtoList().get(waypointsCnt);

        /***
         * 요청 헤더 만드는 공식
         ***/

        return makeRequestForm(origin,destination,waypoints);

    }

    private KakaoRouteAllResponseDto makeRequestForm(DocumentDto origin, DocumentDto destination, DocumentDto waypoints){
        Map<String,Object> uriData = new TreeMap<>();
        uriData.put("origin",new DocumentDto(origin.getName(),origin.getY(), origin.getX()));
        uriData.put("destination",new DocumentDto(destination.getName(),destination.getY(),destination.getX()));

        uriData.put("waypoints",new ArrayList<>(Arrays.asList(
                new DocumentDto(waypoints.getName(), waypoints.getY(), waypoints.getX())
        )));
        uriData.put("priority","RECOMMEND");

        URI uri = URI.create("https://apis-navi.kakaomobility.com/v1/waypoints/directions");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
        HttpEntity <Map<String,Object>> httpEntity = new HttpEntity<>(uriData,headers);

        return restTemplate.postForEntity(uri,httpEntity,KakaoRouteAllResponseDto.class).getBody();
    }
}
