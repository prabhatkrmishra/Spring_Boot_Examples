package com.CodingNinjas.TaxEase.controller;

import com.CodingNinjas.TaxEase.dto.TaxRecordDto;
import com.CodingNinjas.TaxEase.model.TaxRecord;
import com.CodingNinjas.TaxEase.service.TaxRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tax")
public class TaxRecordController {

    @Autowired
    private TaxRecordService taxRecordService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public TaxRecord getTaxRecordById(@PathVariable Long id){
        return taxRecordService.getTaxRecordById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public List<TaxRecord> getALlTaxRecords(){
        return taxRecordService.getAllRecords();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public void createTaxRecord(@RequestBody TaxRecordDto taxRecordDto){
        taxRecordService.createTaxRecord(taxRecordDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public void updateTaxRecord(@RequestBody TaxRecordDto taxRecordDto,
                                @PathVariable Long id){
        taxRecordService.updateTaxRecord(taxRecordDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public void deleteTaxRecord(@PathVariable Long id){
        taxRecordService.deleteTaxRecord(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('NORMAL')")
    public List<TaxRecord> getTaxRecordsByUserName(
            @RequestParam String userName){

        return taxRecordService.getRecordsByName(userName);
    }

    @PostMapping("/approve/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void approveTaxFiling(@PathVariable Long id){
        taxRecordService.approveTaxFiling(id);
    }

    @PostMapping("/reject/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void rejectTaxFiling(@PathVariable Long id){
        taxRecordService.rejectTaxFiling(id);
    }
}