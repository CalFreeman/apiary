package com.sbd.apiary.controller;

import com.sbd.apiary.exception.ResourceNotFoundException;
import com.sbd.apiary.model.Hive;
import com.sbd.apiary.repository.ApiaryRepository;
import com.sbd.apiary.repository.HiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class HiveController {

    @Autowired
    private HiveRepository hiveRepository;

    @Autowired
    private ApiaryRepository apiaryRepository;

    @GetMapping("/hives")
    public Page<Hive> getHive(Pageable pageable) {
        return hiveRepository.findAll(pageable);
    }

    @PostMapping("/hives")
    public Hive createHive(@Valid @RequestBody Hive hive) {
        return hiveRepository.save(hive);
    }

    @PutMapping("apiarys/{apiaryId}/hives/{hiveId}")
    public Hive updateHive(@PathVariable (value = "apiaryId") Long apiaryId,
                               @PathVariable (value = "hiveId") Long hiveId,
                               @Valid @RequestBody Hive hiveRequest) {
        if(!apiaryRepository.existsById(apiaryId)) {
            throw new ResourceNotFoundException("ApiaryId " + apiaryId + " not found");
        }
        return hiveRepository.findById(hiveId).map(hive -> {
            hive.setName(hiveRequest.getName());
            hive.setLocation(hiveRequest.getLocation());
            return hiveRepository.save(hive);
        }).orElseThrow(() -> new ResourceNotFoundException("Hive not found with id " + hiveId));
    }


    @DeleteMapping("/hives/{hiveId}")
    public ResponseEntity<?> deleteHive(@PathVariable Long hiveId) {
        return hiveRepository.findById(hiveId)
                .map(hive -> {
                    hiveRepository.delete(hive);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Hive not found with id " + hiveId));
    }
}