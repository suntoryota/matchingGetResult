package com.example.matchingresult.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ME_ENTRY_MATCHING_RO")
@Data
@Getter
@Setter
public class MeEntryMatchingRo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MOBILE_ID")
    private String mobileId;

    @Column(name = "LIST_MATCHING", columnDefinition = "TEXT")
    private String listMatching;

    @Column(name = "MATCHING_TYPE")
    private String matchingType;

}