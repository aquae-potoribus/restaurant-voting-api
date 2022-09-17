package ru.javaops.topjava2.web;

import org.springframework.web.bind.annotation.*;
import ru.javaops.topjava2.model.User;
import ru.javaops.topjava2.repository.CrudUserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class RootRestController {

    static final String REST_URL = "/api/admin/users";

    private final CrudUserRepository repository;

    RootRestController(CrudUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(REST_URL)
    List<User> all() {
        return repository.findAll();
    }

    // Single item
    @GetMapping(REST_URL + "/{id}")
    User one(@PathVariable Integer id) {

        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(REST_URL)
    User newEmployee(@RequestBody User user) {
        return repository.save(user);
    }

    @PutMapping(REST_URL + "/{id}")
    User replaceEmployee(@RequestBody User user, @PathVariable Integer id) {

        return repository.findById(id)
                .map(user1 -> {
                    user1.setName(user.getName());
                    user1.setEmail(user.getEmail());
                    user1.setPassword(user.getPassword());
                    user1.setRoles(user.getRoles());
                    return repository.save(user1);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });
    }

    @DeleteMapping(REST_URL + "/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
