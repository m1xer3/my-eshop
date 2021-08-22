package ru.danilsibgatullin.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.danilsibgatullin.models.Picture;

public interface PictureRepository extends JpaRepository<Picture,Long> {
}
