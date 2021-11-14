package vl.data.collectionservice.init.test;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.entities.Photo;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.repo.AppUserRepository;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TestCaseLoaderImpl implements TestCaseLoader {

    private final AppUserRepository appUserRepository;


    @Override
    public void saveNewUser(Long userId) {
        AppUser appUser = appUserRepository.findById(userId).orElse(null);
        Photo photo = new Photo();
        photo.setAlbumId(appUser.getAlbums().stream().findFirst().get());
        Long photoId =  Math.round(Math.random()*(99999L)+1L);
        photo.setId(photoId);
        photo.setTitle("test");
        photo.setUrl("test.com");
        photo.setThumbnailUrl("test.com.com");
        appUser.getAlbums().stream().findFirst().get().getPhotos().add(photo);
        appUserRepository.save(appUser);
    }
}
