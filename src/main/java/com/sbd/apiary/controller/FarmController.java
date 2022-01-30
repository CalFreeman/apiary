package com.sbd.apiary.controller;

import com.sbd.apiary.exception.ResourceNotFoundException;
import com.sbd.apiary.model.Farm;
import com.sbd.apiary.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class FarmController {

    @Autowired
    private FarmRepository farmRepository;

    @GetMapping("/farms")
    public Page<Farm> getFarms(Pageable pageable) {
        return farmRepository.findAll(pageable);
    }

    @PostMapping("/farms")
    public Farm createFarm(@Valid @RequestBody Farm farm) {
        return farmRepository.save(farm);
    }

    @PutMapping("/farms/{farmId}")
    public Farm updateFarm(@PathVariable Long farmId,
                                   @Valid @RequestBody Farm farmRequest) {
        return farmRepository.findById(farmId)
                .map(farm -> {
                    farm.setName(farmRequest.getName());
                    farm.setLocation(farmRequest.getLocation());
                    farm.setApiarys(farmRequest.getApiarys());
                    return farmRepository.save(farm);
                }).orElseThrow(() -> new ResourceNotFoundException("Farm not found with id " + farmId));
    }

    @DeleteMapping("/farms/{farmId}")
    public ResponseEntity<?> deleteFarm(@PathVariable Long farmId) {
        return farmRepository.findById(farmId)
                .map(farm -> {
                    farmRepository.delete(farm);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Apiary not found with id " + farmId));
    }
}