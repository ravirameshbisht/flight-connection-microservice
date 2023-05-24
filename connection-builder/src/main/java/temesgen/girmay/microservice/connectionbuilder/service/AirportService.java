package temesgen.girmay.microservice.connectionbuilder.service;

import temesgen.girmay.microservice.connectionbuilder.domain.Airport;

import java.util.List;

public interface AirportService {

    Airport findAirportByAirportCode(final String airportCode);

    Airport findAirportById(final Long id);

    Airport addAirport(Airport airport);

    List<Airport> getAirports();
    void deleteAirport(Long id);
    Airport updateAirport(Airport airport);
}
