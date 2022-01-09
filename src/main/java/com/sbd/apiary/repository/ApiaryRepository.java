package com.sbd.apiary.repository;

import com.sbd.apiary.model.Apiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiaryRepository  extends JpaRepository<Apiary, Long> {
}