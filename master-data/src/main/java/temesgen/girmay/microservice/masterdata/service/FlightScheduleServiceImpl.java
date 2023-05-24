package temesgen.girmay.microservice.masterdata.service;

import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.domain.FlightSchedule;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleDTO;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleRepository;
import temesgen.girmay.microservice.masterdata.exception.FlightScheduleNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService{

    private final FlightScheduleRepository flightScheduleRepository;
    private final AirportService airportService;
    private final FlightScheduleEventPub flightScheduleEventPub;

    public FlightScheduleServiceImpl(final FlightScheduleRepository flightScheduleRepository,
                                     final AirportService airportService,
                                     final FlightScheduleEventPub flightScheduleEventPub) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.airportService = airportService;
        this.flightScheduleEventPub = flightScheduleEventPub;
    }

    public FlightSchedule getFlightScheduleById(Long id){
        return this.flightScheduleRepository.findById(id).orElseThrow();
    }
    @Override
    public List<FlightSchedule> getFlightSchedules() {
        return this.flightScheduleRepository.findAll();
    }

    @Override
    @Transactional
    public FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO) {
        Airport departureAirport = this.airportService.findAirportByAirportCode(flightScheduleDTO.getDepartureAirportCode());
        Airport arrivalAirport = this.airportService.findAirportByAirportCode(flightScheduleDTO.getArrivalAirportCode());
        FlightSchedule newFlightSchedule = FlightSchedule.build(flightScheduleDTO.getFlightNumber(), departureAirport,
                arrivalAirport, flightScheduleDTO.getDepartureTime(),
                flightScheduleDTO.getArrivalTime());

        FlightSchedule createdFlightSchedule =  this.flightScheduleRepository.saveAndFlush(newFlightSchedule);
        this.flightScheduleEventPub.flightScheduleCreated(createdFlightSchedule);
        return createdFlightSchedule;
    }

    @Override
    @Transactional
    public void deleteFlightSchedule(Long id) {
        FlightSchedule flightSchedule = this.flightScheduleRepository.findById(id).orElseThrow(() -> new FlightScheduleNotFoundException("FlightSchedule with id " + id + " not found"));
        this.flightScheduleRepository.deleteById(id);
        this.flightScheduleEventPub.flightScheduleDeleted(flightSchedule);
    }

    @Override
    @Transactional
    public FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule) {
        this.flightScheduleRepository.findById(flightSchedule.getId()).orElseThrow(() -> new FlightScheduleNotFoundException("FlightSchedule with id " + flightSchedule.getId() + " not found"));
        return FlightSchedule.build(flightSchedule.getId(), flightSchedule);
    }
}
