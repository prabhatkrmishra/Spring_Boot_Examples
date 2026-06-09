package CN.CompassionConnect.controller;

import CN.CompassionConnect.dto.DonationDto;
import CN.CompassionConnect.model.Donation;
import CN.CompassionConnect.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('NORMAL')  or hasAuthority('NORMAL')")
    public Donation createDonation(@RequestBody DonationDto donationDto) {
        return donationService.createDonation(donationDto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN') ")
    public List<Donation> getAllDonations(){
        return donationService.getAllDonations();
    }

    @GetMapping("/getTotalAmount")
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADMIN')")
    public Long getTotalDonationAmount(){
        return donationService.getTotalDonationAmount();
    }

}
