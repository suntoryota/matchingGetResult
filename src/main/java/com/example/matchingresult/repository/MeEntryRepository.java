package com.example.matchingresult.repository;

import com.example.matchingresult.entity.MeEntry;
import com.example.matchingresult.service.MatchingServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeEntryRepository extends JpaRepository<MeEntry, Long>, MatchingServiceImpl.CustomRepository {
}
