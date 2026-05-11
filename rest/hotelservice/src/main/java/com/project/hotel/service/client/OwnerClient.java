package com.project.hotel.service.client;

import com.project.hotel.service.exceptions.ServerNotFound;
import com.project.hotel.service.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class OwnerClient {

    private final RestTemplate restTemplate;

    @Autowired
    public OwnerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // GET request on api
    public Owner getOwner(int id) {
        String url = "https://dummyjson.com/users/" + id;

        // Fetch whole data: status + header + body
        ResponseEntity<Owner> responseEntity = null;

        try {
            responseEntity = restTemplate.getForEntity(url, Owner.class);
        } catch (HttpClientErrorException error) {
            throw new ServerNotFound(error.getResponseBodyAsString());
        }

        // Only the response body directly
        //return restTemplate.getForObject(url, Owner.class);

        return responseEntity.getBody();
    }

    // POST for dummyjson.com to return the new created user
    public Owner addOwner(Owner owner) {
        String url = "https://dummyjson.com/users/add";

        //ResponseEntity<Owner> responseEntity = restTemplate.postForEntity(url, owner, Owner.class);

        //return responseEntity.getBody();

        // Using exchange
        HttpEntity<Owner> httpEntity = new HttpEntity<>(owner);
        ResponseEntity<Owner> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Owner.class);
        } catch (HttpClientErrorException error) {
            throw new ServerNotFound(error.getResponseBodyAsString());
        }

        return responseEntity.getBody();
    }

    // PUT for dummyjson.com to return the updated user
    public Owner updateOwner(Owner owner, int id) {
        String url = "https://dummyjson.com/users/" + id;

        // put() internally receives the HTTP response from server,
        // but Spring does not expose it. So no response.getBody().
        // restTemplate.put(url, owner);

        // Using exchange
        HttpEntity<Owner> httpEntity = new HttpEntity<>(owner);
        ResponseEntity<Owner> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Owner.class);
        } catch (HttpClientErrorException error) {
            throw new ServerNotFound(error.getResponseBodyAsString());
        }

        return responseEntity.getBody();
    }

    // DELETE for dummyjson.com to return deleted user with isDeleted & deletedOn keys
    public String deleteOwner(int id) {
        String url = "https://dummyjson.com/users/" + id;

        // delete() internally receives the HTTP response from server,
        // but Spring does not expose it. So no response.getBody().
        // restTemplate.delete(url);

        ResponseEntity<String> responseEntity = null;

        try {
            // Using exchange
            responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        } catch (HttpClientErrorException error) {
            throw new ServerNotFound(error.getResponseBodyAsString());
        }

        return responseEntity.getBody();
    }
}
