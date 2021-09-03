package com.mrhw.distancecalulator.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@JsonAutoDetect
@Data
public class Request {
    private Distance firstDistance;
    private Distance secondDistance;
    private LengthUnit resultUnit;
}
