package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Dish;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.repository.CrudMenuRepository;
import ru.javaops.topjava2.to.DishTo;
import ru.javaops.topjava2.repository.CrudDishRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static ru.javaops.topjava2.util.DishUtil.updateFromTo;
import static ru.javaops.topjava2.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class DishRestController {

    static final String REST_URL = "/api/dish";

    @Autowired
    protected CrudDishRepository repository;

    @Autowired
    protected CrudMenuRepository menurepository;

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

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Dish create(@RequestBody DishTo dishTo) {
        log.info("create {}", dishTo);
        Optional<Menu> optionalMenus = menurepository.getMenuById(dishTo.getMenuId());
        if (!optionalMenus.isPresent()) {
            throw new EntityNotFoundException("Menu with id: " + dishTo.getMenuId() + " does not exist");
        }
        Dish dish = new Dish(dishTo.getName(), dishTo.getPrice(), optionalMenus.get());
        return repository.save(dish);
    }

    @PutMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(@RequestBody DishTo dishTo, @PathVariable Integer id) {
        log.info("update {}", dishTo);
        Optional<Menu> optionalMenus = menurepository.getMenuById(dishTo.getMenuId());
        if (!optionalMenus.isPresent()) {
            throw new EntityNotFoundException("Menu with id: " + dishTo.getMenuId() + " does not exist");
        }
        Menu menu = optionalMenus.get();
        Dish newDish = updateFromTo(dishTo,menu);
        assureIdConsistent(newDish, id);
        repository.updateDishById(menu,dishTo.getName(),dishTo.getPrice(), id);
//         repository.save(newDish);
    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }
}