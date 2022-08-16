package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.model.Exmpl;
import ru.javaops.topjava2.model.Menus;
import ru.javaops.topjava2.repository.DishRepository;
import ru.javaops.topjava2.repository.ExmplRepository;
import ru.javaops.topjava2.repository.MenuRepository;
import ru.javaops.topjava2.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class AdminRestController {

    static final String REST_URL = "/api/admin";

    @Autowired
    protected DishRepository dishRepository;

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Autowired
    protected ExmplRepository exmplRepository;

    @PostMapping(value = "/api/admin/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@RequestBody List<Dish> dishes, @RequestBody Menus menu) {
        log.info("createArray {}", dishes);
        //     menu.setUser(AuthUser);
        menuRepository.save(menu);
        for (Dish dish : dishes) {
            dish.setMenu(menu);
        }
        dishRepository.saveAll(dishes);
    }

    @PostMapping(value = "/api/admin/test", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void test(@RequestBody Map<String, Object> map) {
        log.info("test {}", map);
        List<Dish> dishes = new ArrayList<>();

        String menuName = (String) map.get("menu_name");
        Menus menu = new Menus();
        for (int i = 0; i < (map.size() - 1) / 2; i++) {
            String name = (String) map.get("name" + i);
            Integer price = (Integer) map.get("price" + i);
            dishes.add(new Dish(name, price, menu));
        }
        // menuRepository.save(menu);
        dishRepository.saveAll(dishes);


    }

    @PostMapping(value = "/api/admin/menu/dish", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@RequestBody Dish dish) {
        log.info("create {}", dish);
        dishRepository.save(dish);
        //Menus menus = new;
        //menuRepository.
    }

    @PostMapping(value = "/api/admin/menu/exmpl", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@RequestBody Exmpl exmpl) {
        log.info("create {}", exmpl);
        exmplRepository.save(exmpl);
    }

}