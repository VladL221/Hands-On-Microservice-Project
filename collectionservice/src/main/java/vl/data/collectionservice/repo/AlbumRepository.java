package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album,Long> {

}
