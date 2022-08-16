package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Menus;
import ru.javaops.topjava2.repository.MenuRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class MenuRestController {

    static final String REST_URL = "/api/menu";

    @Autowired
    protected MenuRepository repository;

    @GetMapping(REST_URL)
    List<Menus> all() {
        log.info("get  all{}");

        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    Menus one(@PathVariable Integer id) {
        log.info("get {}", id);

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = REST_URL + "/createArray", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Menus> createArray(@RequestBody List<Menus> menus) {
        log.info("createArray {}", menus);

        return repository.saveAll(menus);
    }

    @PostMapping(value = REST_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menus create(@RequestBody Menus menu) {
        log.info("create {}", menu);

        return repository.save(menu);
    }

    @PutMapping(value = REST_URL + "/{id}}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Menus update(@RequestBody Menus menu, @PathVariable Integer id) {
        log.info("update {}", menu);
        menu.setId(id);
        return repository.save(menu);
    }

    @DeleteMapping(value = REST_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDish(@PathVariable Integer id) {
        log.info("delete {}", id);
        repository.delete(id);
    }
}