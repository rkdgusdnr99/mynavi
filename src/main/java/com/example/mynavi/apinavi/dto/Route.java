package com.example.mynavi.apinavi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @JsonProperty("result_code")
    private int result_code;

    @JsonProperty("result_msg")
    private String result_msg;

    @JsonProperty("summary")
    private Summary summary;

    @JsonProperty("section")
    private List<Section> sections;
}
