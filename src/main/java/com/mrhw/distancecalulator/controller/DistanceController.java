package com.mrhw.distancecalulator.controller;

import com.mrhw.distancecalulator.model.Distance;
import com.mrhw.distancecalulator.model.Request;
import com.mrhw.distancecalulator.service.RateResolver;
import com.mrhw.distancecalulator.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class DistanceController {

    private DistanceService distanceService;
    private RateResolver conversionService;

    @Autowired
    public DistanceController(final DistanceService distanceService, final RateResolver conversionService) {
        this.distanceService = distanceService;
        this.conversionService = conversionService;
    }

    @PostMapping("/add")
    public ResponseEntity addDistances(@RequestBody final Request request) {
        final Distance updated = distanceService.add(request.getFirstDistance(), request.getSecondDistance());
        final Distance converted = getDistance(request, updated);
        return ResponseEntity.ok(converted);
    }

    @PostMapping("/subtract")
    public ResponseEntity subtractDistances(@RequestBody final Request request) {
        final Distance updated = distanceService.subtract(request.getFirstDistance(), request.getSecondDistance());
        final Distance converted = getDistance(request, updated);
        return ResponseEntity.ok(converted);
    }

    @PostMapping("/multiply")
    public ResponseEntity multiplyDistances(@RequestBody final Request request) {
        final Distance multiplied = distanceService.multiply(request.getFirstDistance(), request.getSecondDistance());
        final Distance converted = getDistance(request, multiplied);
        return ResponseEntity.ok(converted);
    }

    @PostMapping("/divide")
    public ResponseEntity divideDistances(@RequestBody final Request request) {
        try {
            final Distance updated = distanceService.divide(request.getFirstDistance(), request.getSecondDistance());
            final Distance converted = getDistance(request, updated);
            return ResponseEntity.ok(converted);
        } catch (ArithmeticException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Distance getDistance(final Request request, final Distance updated) {
        final BigDecimal conversionRate = conversionService.resolve(updated.getLengthUnit(), request.getResultUnit());
        final Distance converted = updated.convertTo(request.getResultUnit(), conversionRate);
        return converted;
    }

}
