package com.project.hotel.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/*
CLIENT REQUEST
      │
      ▼
Controller Method Called
      │
      ▼
OwnerClient.addOwner(owner)
      │
      ▼
restTemplate.exchange(...)
      │
      │  HTTP POST sent to:
      │  https://dummyjson.com/users/add/
      │
      ▼
External Server Response
      │
      ├──────────── SUCCESS (200 / 201) ────────────┐
      │                                             │
      │                                             ▼
      │                                    ResponseEntity<Owner>
      │                                             │
      │                                             ▼
      │                                   responseEntity.getBody()
      │                                             │
      │                                             ▼
      │                                       Owner returned
      │
      │
      └──────────── ERROR (404 / 400 / 500 etc) ───┐
                                                    │
                                                    ▼
                                       RestTemplate throws exception
                                                    │
                                                    ▼
                                        HttpClientErrorException
                                                    │
                                                    ▼
                                   catch (HttpClientErrorException error)
                                                    │
                                                    ▼
                                        throw new ServerNotFound(
                                            error.getResponseBodyAsString()
                                        );
                                                    │
                                                    ▼
                                       Exception propagates upward
                                                    │
                                                    ▼
                                        @RestControllerAdvice detects
                                            ServerNotFound exception
                                                    │
                                                    ▼
                                @ExceptionHandler(ServerNotFound.class) executes
                                                    │
                                                    ▼
                                    return ResponseEntity.status(404)
                                        .body(ex.getMessage());
                                                    │
                                                    ▼
                                        Final HTTP Response to Client
*/
public class ServerNotFound extends HttpClientErrorException {
    public ServerNotFound(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
