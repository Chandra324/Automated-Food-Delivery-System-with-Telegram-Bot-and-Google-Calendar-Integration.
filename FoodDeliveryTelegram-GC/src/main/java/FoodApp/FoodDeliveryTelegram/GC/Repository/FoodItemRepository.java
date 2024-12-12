package FoodApp.FoodDeliveryTelegram.GC.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FoodApp.FoodDeliveryTelegram.GC.Entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {}

