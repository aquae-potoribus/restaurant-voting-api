package ru.javaops.topjava2.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.*;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends BaseRepository<Dish> {

    @Modifying
    @Transactional
    @Query("UPDATE Dish d set d.menu = ?1, d.name = ?2, d.price = ?3 WHERE d.id = ?4")
    void updateDishById(Menu menu, String name, Integer price, Integer id);
}