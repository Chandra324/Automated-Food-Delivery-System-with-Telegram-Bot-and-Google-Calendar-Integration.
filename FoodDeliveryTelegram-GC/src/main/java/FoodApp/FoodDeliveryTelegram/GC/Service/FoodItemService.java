package FoodApp.FoodDeliveryTelegram.GC.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FoodApp.FoodDeliveryTelegram.GC.Entity.FoodItem;

import FoodApp.FoodDeliveryTelegram.GC.Repository.FoodItemRepository;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository repository;

    public List<FoodItem> getAllItems() {
        return repository.findAll();
    }

    public FoodItem addItem(FoodItem item) {
        return repository.save(item);
    }

    public void deleteItem(int id) {
        repository.deleteById(id);
    }
}
