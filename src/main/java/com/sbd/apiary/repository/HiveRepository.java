package com.sbd.apiary.repository;

import com.sbd.apiary.model.Hive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HiveRepository extends JpaRepository<Hive, Long> {
    Page<Hive> findByApiaryId(Long apiaryId, Pageable pageable);
    Optional<Hive> findByIdAndApiaryId(Long id, Long apiaryId);
}