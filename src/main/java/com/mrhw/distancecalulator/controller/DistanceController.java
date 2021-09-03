package com.mrhw.distancecalulator.controller;

import com.mrhw.distancecalulator.model.Distance;
import com.mrhw.distancecalulator.model.Request;
import com.mrhw.distancecalulator.service.ConversionService;
import com.mrhw.distancecalulator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class DistanceController {

    private DistanceService distanceService;
    private ConversionService conversionService;

    @Autowired
    public DistanceController(DistanceService distanceService, ConversionService conversionService) {
        this.distanceService = distanceService;
        this.conversionService = conversionService;
    }

    @PostMapping("/add")
    public ResponseEntity addDistances(@RequestBody Request request) {
        Distance summarized = distanceService.add(request.getFirstDistance(), request.getSecondDistance());
        Distance converted = getDistance(request, summarized);
        return ResponseEntity.ok(converted);
    }

    @PostMapping("/subtract")
    public ResponseEntity subtractDistances(@RequestBody Request request) {
        Distance updated = distanceService.subtract(request.getFirstDistance(), request.getSecondDistance());
        Distance converted = getDistance(request, updated);
        return ResponseEntity.ok(converted);
    }
    @PostMapping("/multiply")
    public ResponseEntity multiplyDistances(@RequestBody Request request) {
        Distance multiplied = distanceService.multiply(request.getFirstDistance(), request.getSecondDistance());
        Distance converted = getDistance(request, multiplied);
        return ResponseEntity.ok(converted);
    }

    @PostMapping("/divide")
    public ResponseEntity divideDistances(@RequestBody Request request) {
        try {
            Distance updated = distanceService.divide(request.getFirstDistance(), request.getSecondDistance());
            Distance converted = getDistance(request, updated);
            return ResponseEntity.ok(converted);
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Distance getDistance(Request request, Distance updated) {
        BigDecimal conversionRate = conversionService.resolveRate(updated.getLengthUnit(), request.getResultUnit());
        Distance converted = updated.convertTo(request.getResultUnit(), conversionRate);
        return converted;
    }

}
