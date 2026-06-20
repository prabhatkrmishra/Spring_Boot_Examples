package Telecom.SupportService.controller;

import Telecom.SupportService.dto.MaintenanceScheduleDto;
import Telecom.SupportService.dto.ResponseMessage;
import Telecom.SupportService.model.MaintenanceSchedule;
import Telecom.SupportService.service.MaintenanceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceScheduleLController {

    @Autowired
    private MaintenanceScheduleService maintenanceScheduleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MaintenanceSchedule> getAllMaintenanceSchedule(){
        return maintenanceScheduleService.getAllMaintenanceSchedules();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MaintenanceSchedule getMaintenanceScheduleById(@PathVariable Long id){
        return maintenanceScheduleService.getMaintenanceScheduleById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage createMaintenanceSchedule(@RequestBody MaintenanceScheduleDto maintenanceScheduleDto) {
        maintenanceScheduleService.createMaintenanceSchedule(maintenanceScheduleDto);
        return new ResponseMessage("Maintenance Schedule created Successfully");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage updateMaintenanceSchedule(@RequestParam Long id, @RequestBody MaintenanceScheduleDto maintenanceScheduleDto) {
        maintenanceScheduleService.updateMaintenanceSchedule(id, maintenanceScheduleDto);
        return new ResponseMessage("Maintenance Schedule updated Successfully");
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage deleteMaintenanceSchedule(Long id) {
        maintenanceScheduleService.deleteMaintenanceSchedule(id);
        return new ResponseMessage("Maintenance Schedule Deleted Successfully");
    }
}
