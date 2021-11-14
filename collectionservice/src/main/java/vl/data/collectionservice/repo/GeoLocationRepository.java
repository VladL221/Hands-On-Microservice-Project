package vl.data.collectionservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vl.data.collectionservice.entities.user.GeoLocation;

@Repository
public interface GeoLocationRepository extends JpaRepository<GeoLocation,Long> {

    GeoLocation findByLat(String lat);

}
