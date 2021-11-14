package vl.data.collectionservice.service.album;

import vl.data.collectionservice.entities.Album;

import java.util.List;

public interface AlbumService {

    List<Album> getAlbums();
    Album getAlbum(Long id);
    Album createAlbum(Album album);
    Album updateAlbum(Album album);

}
