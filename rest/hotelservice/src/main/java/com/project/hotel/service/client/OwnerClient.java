package com.project.hotel.service.client;

import com.project.hotel.service.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OwnerClient {

    private final RestTemplate restTemplate;

    @Autowired
    public OwnerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Owner getOwner(Integer id) {
        String url = "https://dummyjson.com/users/" + id;

        // Fetch whole data: status + header + body
        ResponseEntity<Owner> responseEntity = restTemplate.getForEntity(url, Owner.class);

        // Only the response body directly
        //return restTemplate.getForObject(url, Owner.class);

        return responseEntity.getBody();
    }
}
