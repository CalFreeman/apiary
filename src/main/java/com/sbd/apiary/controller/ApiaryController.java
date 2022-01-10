package com.sbd.apiary.controller;

import com.sbd.apiary.exception.ResourceNotFoundException;
import com.sbd.apiary.model.Apiary;
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

    @GetMapping("/apiarys")
    public Page<Apiary> getApiary(Pageable pageable) {
        return apiaryRepository.findAll(pageable);
    }


    @PostMapping("/apiarys")
    public Apiary createApiary(@Valid @RequestBody Apiary apiary) {
        return apiaryRepository.save(apiary);
    }

    @PutMapping("/apiarys/{apiaryId}")
    public Apiary updateApiary(@PathVariable Long apiaryId,
                               @Valid @RequestBody Apiary apiaryRequest) {
        return apiaryRepository.findById(apiaryId)
                .map(apiary -> {
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