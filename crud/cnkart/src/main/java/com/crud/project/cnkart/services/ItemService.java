package com.crud.project.cnkart.services;

import com.crud.project.cnkart.exceptions.ItemNotFoundException;
import com.crud.project.cnkart.models.Item;
import com.crud.project.cnkart.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItemById(int id) {

        Optional<Item> optionalItem =
                itemRepository.findById(id);

        if(optionalItem.isPresent()) {
            return optionalItem.get();
        }

        throw new ItemNotFoundException("Item with id: " + id + " not found");
    }
}
