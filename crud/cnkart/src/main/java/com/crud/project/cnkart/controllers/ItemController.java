package com.crud.project.cnkart.controllers;

import com.crud.project.cnkart.models.Item;
import com.crud.project.cnkart.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id){
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }


}
