package Telecom.SubscriptionService.feign;

import Telecom.SubscriptionService.BillingDtos.InvoiceDto;
import Telecom.SubscriptionService.dto.ResponseMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "billing-service")
public interface BillingService {

    @PostMapping("/Invoice")
    ResponseMessage createInvoice(@RequestBody InvoiceDto invoiceDto);
}
