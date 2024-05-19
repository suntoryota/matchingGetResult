package com.example.matchingresult.repository;

import com.example.matchingresult.entity.MeEntryPersonalSpouse;
import com.example.matchingresult.service.MatchingServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeEntryPersonalSpouseRepository extends JpaRepository<MeEntryPersonalSpouse, Long>, MatchingServiceImpl.CustomRepository {
}
