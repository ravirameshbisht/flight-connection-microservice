package temesgen.girmay.microservice.connectionbuilder.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Airport findByAirportCode(String airportCode);

    Optional<Airport> findById(Long id);
}
