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
import ru.javaops.topjava2.to.MenuTo;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.validation.ValidationUtil.checkOptional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class MenuRestController {

    static final String REST_URL = "/api/menu";

    @Autowired
    protected CrudMenuRepository repository;

    @Autowired
    protected CrudUserRepository userRepository;

    @Autowired
    protected CrudRestaurantRepository restaurantRepository;

    @GetMapping(REST_URL)
    List<Menu> all() {
        log.info("get  all{}");

        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    Menu one(@PathVariable Integer id) {
        log.info("get {}", id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu create(@RequestBody MenuTo menuTo) {
        log.info("create {}", menuTo);
        Optional<Restaurant> restaurant = restaurantRepository.findById(menuTo.getRestaurantId());
        checkOptional(restaurant,menuTo.getRestaurantId());
        Optional<User> user = userRepository.findById(menuTo.getUserId());
        checkOptional(user,menuTo.getUserId());
        return repository.save(new Menu(restaurant.get(),user.get()));
    }

    @PutMapping(value = REST_URL + "/{id}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Menu update(@RequestBody Menu menu, @PathVariable Integer id) {
        log.info("update {}", menu);
        menu.setId(id);
        return repository.save(menu);
    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createMenu(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }
}