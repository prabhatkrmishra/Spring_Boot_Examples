package com.project.hotel.service.services;

import com.project.hotel.service.client.OwnerClient;
import com.project.hotel.service.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerClient ownerClient;

    public Owner addOwnerDetails(int fetchId) {
        Owner owner = ownerClient.getOwner(fetchId);
        owner.printDetails();
        return owner;
    }
}
