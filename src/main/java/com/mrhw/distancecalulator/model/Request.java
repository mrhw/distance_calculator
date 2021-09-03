package com.mrhw.distancecalulator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@JsonAutoDetect
@Data
public class Request {
    @JsonIgnore(value = false)
    private Distance firstDistance;
    @JsonIgnore(value = false)
    private Distance secondDistance;
    @JsonIgnore(value = false)
    private LengthUnit resultUnit = LengthUnit.M;
}
