package vl.data.collectionservice.service.photo;

import vl.data.collectionservice.entities.Photo;

import java.util.List;

public interface PhotoService {

    List<Photo> getPhotos();
    Photo getPhoto(Long id);
    void deletePhoto(Long id);

}
