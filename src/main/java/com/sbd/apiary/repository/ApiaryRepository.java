package com.sbd.apiary.repository;

import com.sbd.apiary.model.Apiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiaryRepository extends JpaRepository<Apiary, Long> {
    Page<Apiary> findByFarmId(Long farmId, Pageable pageable);
    Optional<Apiary> findByIdAndFarmId(Long id, Long farmId);
}