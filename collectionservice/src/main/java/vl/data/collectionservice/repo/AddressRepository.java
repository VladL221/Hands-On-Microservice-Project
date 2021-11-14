package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.user.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByCity(String city);

}
