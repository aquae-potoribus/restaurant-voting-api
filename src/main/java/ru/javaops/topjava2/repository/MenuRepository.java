package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Menus;

import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menus> {
    Optional<Menus> getMenuById(Integer id);
}