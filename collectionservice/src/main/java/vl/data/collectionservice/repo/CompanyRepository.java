package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.user.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

    Company findByName(String name);

}
