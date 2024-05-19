package com.example.matchingresult.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ME_ENTRY_PERSONAL_GUARANTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeEntryPersonalGuarantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customerId")
    private String customerId;

    @Column(name = "dataId")
    private String dataId;

    @Column(name = "nama_konsumen", nullable = false)
    private String namaKonsumen;

    @Column(name = "nomor_identitas", nullable = false, unique = true)
    private String nomorIdentitas;

    @Column(name = "tanggal_lahir", nullable = false)
    private String tanggalLahir;

    @Column(name = "match_type")
    private String matchType;

    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @Column(name = "npwp", unique = true)
    private String npwp;

    @Column(name = "nama_gadis_ibu_kandung")
    private String namaGadisIbuKandung;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "kode_pos")
    private String kodePos;

    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @Column(name = "kabupaten", nullable = false)
    private String kabupaten;

    @Column(name = "kecamatan", nullable = false)
    private String kecamatan;

    @Column(name = "kelurahan", nullable = false)
    private String kelurahan;
}
