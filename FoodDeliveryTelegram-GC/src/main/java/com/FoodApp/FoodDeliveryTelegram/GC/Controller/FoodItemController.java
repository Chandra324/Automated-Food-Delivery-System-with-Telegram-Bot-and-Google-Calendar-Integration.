package com.FoodApp.FoodDeliveryTelegram.GC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.FoodApp.FoodDeliveryTelegram.GC.Entity.FoodItem;

import FoodApp.FoodDeliveryTelegram.GC.Service.FoodItemService;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {

    @Autowired
    private FoodItemService service;

    @GetMapping("/items")
    public List<FoodItem> getAllItems() {
        return service.getAllItems();
    }

    @PostMapping("/add")
    public FoodItem addItem(@RequestBody FoodItem item) {
        return service.addItem(item);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable int id) {
        service.deleteItem(id);
    }
}
