package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {

}
