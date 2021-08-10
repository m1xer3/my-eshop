package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilsibgatullin.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
