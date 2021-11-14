package vl.data.collectionservice.service.photo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Photo;
import vl.data.collectionservice.repo.AlbumRepository;
import vl.data.collectionservice.repo.PhotoRepository;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PhotoServiceImpl implements PhotoService{



    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    @Override
    public List<Photo> getPhotos() {
        try {
            return photoRepository.findAll();
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Photo getPhoto(Long id) {
        try {
            return photoRepository.findById(id).orElse(null);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }


    @Override
    public void deletePhoto(Long id) {
        try {
            Photo photo = photoRepository.findById(id).orElse(null);
            Album album = albumRepository.findById(photo.getAlbumId().getId()).orElse(null);
            photoRepository.delete(photo);
            log.info("Photo deleted?: {} ", photoRepository.findById(id).orElse(null));
            log.info("Album deleted?: {}", albumRepository.findById(album.getId()).orElse(null));
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
        }
    }
}
