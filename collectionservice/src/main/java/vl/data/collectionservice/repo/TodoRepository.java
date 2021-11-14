package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
}
