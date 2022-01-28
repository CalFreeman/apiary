package com.sbd.apiary.controller;

import com.sbd.apiary.exception.ResourceNotFoundException;
import com.sbd.apiary.model.Apiary;
import com.sbd.apiary.repository.FarmRepository;
import com.sbd.apiary.repository.ApiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class ApiaryController {

    @Autowired
    private ApiaryRepository apiaryRepository;

    @Autowired
    private FarmRepository farmRepository;

    @GetMapping("/apiarys")
    public Page<Apiary> getApiary(Pageable pageable) {
        return apiaryRepository.findAll(pageable);
    }


    @PostMapping("/apiarys")
    public Apiary createApiary(@Valid @RequestBody Apiary apiary) {
        return apiaryRepository.save(apiary);
    }

    @PutMapping("farms/{farmId}/apiarys/{apiaryId}")
    public Apiary updateApiary(@PathVariable (value = "farmId") Long farmId,
                               @PathVariable (value = "apiaryId") Long apiaryId,
                               @Valid @RequestBody Apiary apiaryRequest) {
        if(!farmRepository.existsById(farmId)) {
            throw new ResourceNotFoundException("FarmId " + farmId + " not found");
        }
        return apiaryRepository.findById(apiaryId).map(apiary -> {
                    apiary.setName(apiaryRequest.getName());
                    apiary.setLocation(apiaryRequest.getLocation());
                    return apiaryRepository.save(apiary);
                }).orElseThrow(() -> new ResourceNotFoundException("Apiary not found with id " + apiaryId));
    }


    @DeleteMapping("/apiarys/{apiaryId}")
    public ResponseEntity<?> deleteApiary(@PathVariable Long apiaryId) {
        return apiaryRepository.findById(apiaryId)
                .map(apiary -> {
                    apiaryRepository.delete(apiary);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Apiary not found with id " + apiaryId));
    }
}