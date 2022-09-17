package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.model.Restaurant;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.repository.CrudMenuRepository;
import ru.javaops.topjava2.repository.CrudRestaurantRepository;
import ru.javaops.topjava2.repository.CrudUserRepository;
import ru.javaops.topjava2.to.RestaurantTo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.validation.ValidationUtil.checkOptional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class RestaurantRestController {

    static final String REST_URL = "/api/restaurant";

    @Autowired
    protected CrudRestaurantRepository repository;

    @Autowired
    protected CrudUserRepository userRepository;

     @Autowired
    protected CrudMenuRepository menuRepository;



    @GetMapping(REST_URL)
    List<Restaurant> all() {
        log.info("get  all{}");

        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    Restaurant one(@PathVariable Integer id) {
        log.info("get {}", id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody RestaurantTo restaurantTo) {
        log.info("create {}", restaurantTo);
        Optional<Menu> menu = menuRepository.findById(restaurantTo.getMenuId());
        checkOptional(menu,restaurantTo.getMenuId());
        Optional<User> user = userRepository.findById(restaurantTo.getUserId());
        checkOptional(user,restaurantTo.getUserId());
        return repository.save(new Restaurant(menu.get(),user.get()));
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Restaurant update(@RequestBody Restaurant restaurant, @PathVariable Integer id) {
        log.info("update {}", restaurant);
        restaurant.setId(id);
        return repository.save(restaurant);

    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

}