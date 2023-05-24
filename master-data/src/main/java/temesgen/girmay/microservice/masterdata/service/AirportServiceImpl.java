package temesgen.girmay.microservice.masterdata.service;

import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.masterdata.domain.*;
import temesgen.girmay.microservice.masterdata.exception.AirportNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final FlightScheduleRepository flightScheduleRepository;
    private final AirportEventPub airportEventPub;

    public AirportServiceImpl(final AirportRepository airportRepository,
                              final FlightScheduleRepository flightScheduleRepository,
                              final AirportEventPub airportEventPub) {
        this.airportRepository = airportRepository;
        this.flightScheduleRepository = flightScheduleRepository;
        this.airportEventPub = airportEventPub;
    }

    @Override
    public Airport findAirportById(Long id) {
        return airportRepository.findById(id).orElseThrow();
    }

    @Override
    public Airport findAirportByAirportCode(String airportCode) {
        return this.airportRepository.findByAirportCode(airportCode);
    }

    @Override
    public List<Airport> getAirports() {
        return this.airportRepository.findAll();
    }

    @Override
    @Transactional
    public Airport addAirport(Airport airport) {
        Airport createdAirport =  this.airportRepository.save(airport);
        this.airportEventPub.airportCreated(createdAirport);
        return createdAirport;
    }

    @Override
    @Transactional
    public void deleteAirport(Long id) {
        Airport airport = this.airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException("Airport with id " + id + " not found"));
        List<FlightSchedule> flightSchedules = this.flightScheduleRepository.findAllFlightSchedulesForAirport(id);
        this.flightScheduleRepository.deleteAll(flightSchedules);
        this.airportRepository.deleteById(id);
        this.airportEventPub.airportDeleted(airport);
    }

    @Override
    @Transactional
    public Airport updateAirport(Airport airport) {
        this.airportRepository.findById(airport.getId()).orElseThrow(() -> new AirportNotFoundException("Airport with id " + airport.getId() + " not found"));
        Airport.build(airport.getId(), airport);
        Airport updatedAirport = this.airportRepository.saveAndFlush(Airport.build(airport.getId(), airport));
        this.airportEventPub.airportUpdated(updatedAirport);
        return updatedAirport;
        }
}
