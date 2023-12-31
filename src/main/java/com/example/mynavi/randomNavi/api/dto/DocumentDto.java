package com.example.mynavi.randomNavi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

    @JsonProperty("address_name")
    private String name;

    @JsonProperty("y") // y좌표값
    private double y;

    @JsonProperty("x") // x좌표값
    private double x;
}