package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Menu;

import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends BaseRepository<Menu> {
    Optional<Menu> getMenuById(Integer id);
}