package com.example.mynavi.apinavi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
// 경로에 관한 모든 응답
public class KakaoRouteAllResponseDto {
    private String trans_id;
    private List<Route> routes;

}
