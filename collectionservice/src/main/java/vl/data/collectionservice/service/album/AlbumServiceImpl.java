package vl.data.collectionservice.service.album;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.repo.AlbumRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AlbumServiceImpl implements AlbumService{


    private final AlbumRepository albumRepository;

    @Override
    public List<Album> getAlbums() {
        try {
            return albumRepository.findAll();
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Album getAlbum(Long id) {
        try {
            return albumRepository.findById(id).orElse(null);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Album createAlbum(Album album) {
        try {
            return albumRepository.save(album);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Album updateAlbum(Album album) {
        try {
            return albumRepository.save(album);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }


}
