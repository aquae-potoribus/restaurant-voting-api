package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.repository.DishRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class DishRestController {

    static final String REST_URL = "/api/dish";

    @Autowired
    protected DishRepository repository;

    @GetMapping(REST_URL)
    List<Dish> all() {
        log.info("get  all{}");

        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    Dish one(@PathVariable Integer id) {
        log.info("get {}", id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = REST_URL + "/createArray", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> createArray(@RequestBody List<Dish> dishes) {
        log.info("createArray {}", dishes);

        return repository.saveAll(dishes);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@RequestBody Dish dish) {
        log.info("create {}", dish);

        return repository.save(dish);
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Dish update(@RequestBody Dish dish, @PathVariable Integer id) {
        log.info("update {}", dish);
        assureIdConsistent(dish, id);
        dish.setId(id);
        return repository.save(dish);

    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }
}