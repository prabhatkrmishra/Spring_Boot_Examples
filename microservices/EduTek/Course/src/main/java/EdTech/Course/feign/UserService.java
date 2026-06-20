package EdTech.Course.feign;

import EdTech.Course.dto.LoginRequest;
import EdTech.Course.dto.LoginResponse;
import EdTech.Course.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface UserService {

    @PostMapping("/auth/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

    @GetMapping("/user/{id}")
    User getUserById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id);
}
