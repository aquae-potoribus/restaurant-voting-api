package ru.javaops.topjava2.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.javaops.topjava2.model.Exmpl;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ExmplRepository extends BaseRepository<Exmpl> {
    Optional<Exmpl> getExmplById(Long id);
}