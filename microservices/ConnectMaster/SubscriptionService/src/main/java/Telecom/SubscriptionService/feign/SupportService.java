package Telecom.SubscriptionService.feign;

import Telecom.SubscriptionService.model.Ticket;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(name = "support-service")
public interface SupportService {

    @GetMapping("ticket/userId/{userId}")
    public List<Ticket> getTicketByUserId(@PathVariable Long userId);

}
