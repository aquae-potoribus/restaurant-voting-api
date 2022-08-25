package ru.javaops.topjava2.web.myControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.Menu;
import ru.javaops.topjava2.repository.CrudMenuRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
// TODO: cache only most requested data!
public class MenuRestController {

    static final String REST_URL = "/api/menu";

    @Autowired
    protected CrudMenuRepository repository;

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
    public Menu create(@RequestBody Menu menu) {
        log.info("create {}", menu);

        return repository.save(menu);
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