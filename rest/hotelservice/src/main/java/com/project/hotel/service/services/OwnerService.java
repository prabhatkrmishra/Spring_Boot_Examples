package com.project.hotel.service.services;

import com.project.hotel.service.client.OwnerClient;
import com.project.hotel.service.models.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class OwnerService {

    @Autowired
    private OwnerClient ownerClient;

    ObjectMapper mapper = new ObjectMapper();

    public Owner fetchOwnerDetails(int fetchId) {
        Owner owner = ownerClient.getOwner(fetchId);
        owner.printDetails();
        return owner;
    }

    public Owner addOwnerDetails(Owner owner) {
        Owner nowner = ownerClient.addOwner(owner);
        nowner.printDetails();
        return nowner;
    }

    public Owner updateOwner(Owner owner, int id) {
        Owner nowner = ownerClient.updateOwner(owner, id);
        nowner.printDetails();
        return nowner;
    }

    public void deleteOwner(int id) {
        String response = ownerClient.deleteOwner(id);
        String json = mapper.writeValueAsString(response);
        System.out.println(json);
    }
}
