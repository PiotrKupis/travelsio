package com.travelsio.hotelservice.repository;

import com.travelsio.hotelservice.entity.Location;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM Location loc "
        + "WHERE UPPER(loc.label) LIKE UPPER(CONCAT('%', :label, '%')) "
        + "ORDER BY loc.hotels_quantity DESC LIMIT 1", nativeQuery = true)
    Optional<Location> findMostPopularByLabel(@Param("label") String label);
}
