package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
