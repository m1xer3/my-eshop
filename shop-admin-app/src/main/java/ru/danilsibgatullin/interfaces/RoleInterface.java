package ru.danilsibgatullin.interfaces;

import ru.danilsibgatullin.models.Role;

import java.util.List;
import java.util.Optional;


public interface RoleInterface {

    List<Role> findAll();

    void save(Role role);

    void deleteById(Long id);

    Optional<Role> findById(Long id);

}
