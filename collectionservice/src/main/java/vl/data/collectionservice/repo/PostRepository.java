package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Post findByTitle(String title);


}
