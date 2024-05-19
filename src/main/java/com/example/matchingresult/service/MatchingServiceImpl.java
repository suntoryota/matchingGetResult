package com.example.matchingresult.service;

import com.example.matchingresult.dto.MatchingData;
import com.example.matchingresult.dto.MatchingResultDTO;
import com.example.matchingresult.entity.MeEntryMatchingRo;
import com.example.matchingresult.repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchingServiceImpl implements MatchingService {

    private final MeEntryMatchingRoRepository meEntryMatchingRoRepository;
    private final MeEntryRepository meEntryRepository;
    private final MeEntryPersonalSpouseRepository meEntryPersonalSpouseRepository;
    private final MeEntryPersonalGuarantorRepository meEntryPersonalGuarantorRepository;
    private final MeEntryPersonalGuarantorSpouseRepository meEntryPersonalGuarantorSpouseRepository;
    private final ObjectMapper objectMapper;

    @Override
    public MatchingResultDTO getMatchingResult(String mobileId) {
        log.info("Retrieving matching result for mobileId: {}", mobileId);

        List<MeEntryMatchingRo> matchingRoList = meEntryMatchingRoRepository.findAllByMobileId(mobileId);

        if (matchingRoList.isEmpty()) {
            log.warn("Matching data not found for mobileId: {}", mobileId);
            throw new RuntimeException("Matching data not found for mobileId: " + mobileId);
        }

        MatchingResultDTO result = new MatchingResultDTO();

        for (MeEntryMatchingRo matchingRo : matchingRoList) {
            log.debug("Processing matchingRo with ID: {}", matchingRo.getId());

            List<MatchingData> matchingDataList = parseMatchingData(matchingRo.getListMatching());

            for (MatchingData data : matchingDataList) {
                log.debug("Processing matching data for customerId: {}", data.getCustomerId());

                boolean matched = false;

                // Check if the current data matches the repository for each matching type
                if ("KONSUMEN".equalsIgnoreCase(matchingRo.getMatchingType())) {
                    matched = compareWithRepository(meEntryRepository, data);
                    if (matched) {
                        result.setKonsumen(mapToKonsumen(data));
                        result.setKonsumenResult(true);
                    }
                }

                if ("PASANGAN_KONSUMEN".equalsIgnoreCase(matchingRo.getMatchingType())) {
                    matched = compareWithRepository(meEntryPersonalSpouseRepository, data);
                    if (matched) {
                        result.setPasangan(mapToKonsumen(data));
                        result.setPasanganResult(true);
                    }
                }

                if ("PENJAMIN".equalsIgnoreCase(matchingRo.getMatchingType())) {
                    matched = compareWithRepository(meEntryPersonalGuarantorRepository, data);
                    if (matched) {
                        result.setPenjamin(mapToKonsumen(data));
                        result.setPenjaminResult(true);
                    }
                }

                if ("PENJAMIN_PASANGAN".equalsIgnoreCase(matchingRo.getMatchingType())) {
                    matched = compareWithRepository(meEntryPersonalGuarantorSpouseRepository, data);
                    if (matched) {
                        result.setPasanganPenjamin(mapToKonsumen(data));
                        result.setPasanganPenjaminResult(true);
                    }
                }

                log.debug("Matching result for mobileId: {}, matchingType: {}, customerId: {}, matched: {}", mobileId, matchingRo.getMatchingType(), data.getCustomerId(), matched);
            }
        }

        log.info("Matching result retrieved successfully for mobileId: {}", mobileId);
        return result;
    }

    private List<MatchingData> parseMatchingData(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, new TypeReference<List<MatchingData>>() {});
        } catch (Exception e) {
            log.error("Failed to parse JSON data", e);
            throw new RuntimeException("Failed to parse JSON data", e);
        }
    }

    private MatchingResultDTO.Konsumen mapToKonsumen(MatchingData data) {
        MatchingResultDTO.Konsumen konsumen = new MatchingResultDTO.Konsumen();
        konsumen.setNamaKonsumen(data.getNamaKonsumen());
        konsumen.setNomorIdentitas(data.getNomorIdentitas());
        konsumen.setTanggalLahir(data.getTanggalLahir());
        konsumen.setCustomerId(data.getCustomerId());
        konsumen.setMatchType(data.getMatchType());
        konsumen.setTempatLahir(data.getTempatLahir());
        konsumen.setNpwp(data.getNpwp());
        konsumen.setNamaGadisIbuKandung(data.getNamaGadisIbuKandung());
        konsumen.setAlamat(data.getAlamat());
        konsumen.setKodePos(data.getKodePos());
        konsumen.setProvinsi(data.getProvinsi());
        konsumen.setKabupaten(data.getKabupaten());
        konsumen.setKecamatan(data.getKecamatan());
        konsumen.setKelurahan(data.getKelurahan());
        return konsumen;
    }

    private boolean compareWithRepository(CustomRepository repository, MatchingData data) {
        return repository.existsByCustomerIdOrDataId(data.getCustomerId(), data.getDataId());
    }

    public interface CustomRepository {
        boolean existsByCustomerIdOrDataId(String customerId, String dataId);
    }
}