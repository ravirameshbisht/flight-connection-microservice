package temesgen.girmay.microservice.masterdata.service;

import temesgen.girmay.microservice.masterdata.domain.Airport;

import java.util.List;

public interface AirportService {

    Airport findAirportById(Long id);
    Airport findAirportByAirportCode(String airportCode);
    List<Airport> getAirports();
    Airport addAirport(Airport airport);
    void deleteAirport(Long id);
    Airport updateAirport(Airport airport);
}
