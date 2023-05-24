package temesgen.girmay.microservice.connectionbuilder.service;

import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.connectionbuilder.domain.Airport;
import temesgen.girmay.microservice.connectionbuilder.domain.AirportRepository;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightSchedule;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightScheduleRepository;
import temesgen.girmay.microservice.connectionbuilder.exception.AirportNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final FlightScheduleRepository flightScheduleRepository;

    public AirportServiceImpl(final AirportRepository airportRepository,
                              final FlightScheduleRepository flightScheduleRepository) {
        this.airportRepository = airportRepository;
        this.flightScheduleRepository = flightScheduleRepository;
    }

    @Override
    public Airport findAirportByAirportCode(String airportCode) {
        return this.airportRepository.findByAirportCode(airportCode);
    }

    @Override
    public Airport findAirportById(Long id) {
        return this.airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException("Airport with id " + id + " not found"));
    }

    @Override
    public Airport addAirport(Airport airport) {
        return this.airportRepository.saveAndFlush(airport);
    }

    @Override
    public List<Airport> getAirports() {
        return this.airportRepository.findAll();
    }

    @Override
    public void deleteAirport(Long id) {
        this.airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException("Airport with id " + id + " not found"));
        List<FlightSchedule> flightSchedules = this.flightScheduleRepository.findAllFlightSchedulesForAirport(id);
        this.flightScheduleRepository.deleteAll(flightSchedules);
        this.airportRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport) {
        this.airportRepository.findById(airport.getId()).orElseThrow(() -> new AirportNotFoundException("Airport with id " + airport.getId() + " not found"));
        return Airport.build(airport.getId(), airport);
    }
}
