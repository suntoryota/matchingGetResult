package com.example.matchingresult.repository;

import com.example.matchingresult.entity.MeEntryMatchingRo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeEntryMatchingRoRepository extends JpaRepository<MeEntryMatchingRo, Long> {
    Optional<MeEntryMatchingRo> findByMobileId(String mobileId);

    List<MeEntryMatchingRo> findAllByMobileId(String mobileId);
}