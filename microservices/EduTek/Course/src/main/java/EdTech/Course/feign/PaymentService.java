package EdTech.Course.feign;

import EdTech.Course.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentService {

    @PostMapping("/payment")
    Object createPayment(@RequestBody PaymentRequest paymentRequest);
}