package com.CodingNinjas.TaxEase.service;

import com.CodingNinjas.TaxEase.dto.TaxRecordDto;
import com.CodingNinjas.TaxEase.exception.TaxRecordNotFoundException;
import com.CodingNinjas.TaxEase.model.TaxRecord;
import com.CodingNinjas.TaxEase.repository.TaxRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaxRecordService {

    @Autowired
    private TaxRecordRepository taxRecordRepository;

    public TaxRecord getTaxRecordById(Long id) {
        return taxRecordRepository.findById(id)
                .orElseThrow(() -> new TaxRecordNotFoundException("Tax record not found with id: " + id));
    }

    public List<TaxRecord> getAllRecords() {
        return taxRecordRepository.findAll();
    }

    public void createTaxRecord(TaxRecordDto taxRecordDto) {
        TaxRecord taxRecord = new TaxRecord();
        taxRecord.setUserName(taxRecordDto.getUserName());
        taxRecord.setTaxYear(taxRecordDto.getTaxYear());
        taxRecord.setIncome(taxRecordDto.getIncome());
        taxRecord.setDeductions(taxRecordDto.getDeductions());
        taxRecord.setFilingApproved(false);
        taxRecordRepository.save(taxRecord);
    }

    public void updateTaxRecord(TaxRecordDto taxRecordDto, Long id) {
        TaxRecord existingRecord = getTaxRecordById(id);
        existingRecord.setUserName(taxRecordDto.getUserName());
        existingRecord.setTaxYear(taxRecordDto.getTaxYear());
        existingRecord.setIncome(taxRecordDto.getIncome());
        existingRecord.setDeductions(taxRecordDto.getDeductions());
        taxRecordRepository.save(existingRecord);
    }

    public void deleteTaxRecord(Long id) {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecordRepository.delete(taxRecord);
    }

    public List<TaxRecord> getRecordsByName(String userName) {
        return taxRecordRepository.findByUserName(userName);
    }

    public void approveTaxFiling(Long id) {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(true);
        taxRecordRepository.save(taxRecord);
    }

    public void rejectTaxFiling(Long id) {
        TaxRecord taxRecord = getTaxRecordById(id);
        taxRecord.setFilingApproved(false);
        taxRecordRepository.save(taxRecord);
    }
}