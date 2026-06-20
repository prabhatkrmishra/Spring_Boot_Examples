package Telecom.SubscriptionService.BillingDtos;

import Telecom.SubscriptionService.model.Payment;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {
    private Long subscriptionId;
    private String customerName;
    private String invoiceDate;
    private Integer amount;
    private List<Payment> paymentList;
}
