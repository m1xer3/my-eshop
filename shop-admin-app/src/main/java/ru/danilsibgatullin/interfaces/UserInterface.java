package ru.danilsibgatullin.interfaces;

import org.springframework.data.domain.Page;
import ru.danilsibgatullin.controllers.UserDto;
import ru.danilsibgatullin.models.UserParams;
import java.util.List;
import java.util.Optional;

public interface UserInterface {
    List<UserDto> findAll();

    Page<UserDto> findWithFilter(UserParams userParams);

    Optional<UserDto> findById( Long id);

    void save(UserDto user);

    void deleteById(Long id);
}
