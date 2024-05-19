package com.example.matchingresult.repository;

import com.example.matchingresult.entity.MeEntryPersonalGuarantor;
import com.example.matchingresult.service.MatchingServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeEntryPersonalGuarantorRepository extends JpaRepository<MeEntryPersonalGuarantor, Long>, MatchingServiceImpl.CustomRepository {
}
