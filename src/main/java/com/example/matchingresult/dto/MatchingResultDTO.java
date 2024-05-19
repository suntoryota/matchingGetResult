package com.example.matchingresult.dto;

import lombok.Data;

@Data
public class MatchingResultDTO {
    private boolean konsumenResult;
    private boolean pasanganResult;
    private boolean penjaminResult;
    private boolean pasanganPenjaminResult;

    private Konsumen konsumen;
    private Konsumen pasangan;
    private Konsumen penjamin;
    private Konsumen pasanganPenjamin;

    @Data
    public static class Konsumen {
        private String namaKonsumen;
        private String nomorIdentitas;
        private String tanggalLahir;
        private String customerId;
        private String matchType;
        private String tempatLahir;
        private String npwp;
        private String namaGadisIbuKandung;
        private String alamat;
        private String kodePos;
        private String provinsi;
        private String kabupaten;
        private String kecamatan;
        private String kelurahan;
    }
}

