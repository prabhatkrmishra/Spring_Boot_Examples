package Telecom.SupportService.controller;

import Telecom.SupportService.dto.ResponseMessage;
import Telecom.SupportService.dto.TroubleshootingLogDto;
import Telecom.SupportService.model.TroubleshootingLog;
import Telecom.SupportService.service.TroubleshootingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/troubleshooting")
public class TroubleshootingLogController {

    @Autowired
    private TroubleshootingLogService troubleshootingLogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TroubleshootingLog> getAllTroubleshootingLog(){
        return troubleshootingLogService.getAllTroubleshootingLogs();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TroubleshootingLog getTroubleshootingLogById(@PathVariable Long id) {
        return troubleshootingLogService.getTroubleshootingLogById(id);
    }

    @GetMapping("/ticketId/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TroubleshootingLog> getTroubleshootingLogByTicketId(@PathVariable Long ticketId) {
        return troubleshootingLogService.getTroubleshootingLogByTicketId(ticketId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage createTroubleshootingLog(@RequestBody TroubleshootingLogDto troubleshootingLogDto) {
        troubleshootingLogService.createTroubleshootingLog(troubleshootingLogDto);
        return new ResponseMessage("Maintenance Schedule created Successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage updateTroubleshootingLog(@RequestParam Long id, @RequestBody TroubleshootingLogDto troubleshootingLogDto) {
        troubleshootingLogService.updateTroubleshootingLog(id, troubleshootingLogDto);
        return new ResponseMessage("Maintenance Schedule updated Successfully");
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage deleteTroubleshootingLog(Long id) {
        troubleshootingLogService.deleteTroubleshootingLog(id);
        return new ResponseMessage("Maintenance Schedule Deleted Successfully");
    }

}
