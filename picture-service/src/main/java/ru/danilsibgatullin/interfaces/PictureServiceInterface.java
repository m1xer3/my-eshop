package ru.danilsibgatullin.interfaces;

import java.util.Optional;

public interface PictureServiceInterface {

    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id);

    String createPicture(byte[] picture);

    void deletePicture(Long id);
}
