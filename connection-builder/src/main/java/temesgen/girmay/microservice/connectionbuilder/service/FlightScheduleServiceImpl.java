package temesgen.girmay.microservice.connectionbuilder.service;

import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightSchedule;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightScheduleRepository;
import temesgen.girmay.microservice.connectionbuilder.exception.FlightScheduleNotFoundException;

import java.util.List;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;

    public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
    }

    @Override
    public List<FlightSchedule> findAllSchedules() {
        return this.flightScheduleRepository.findAll();
    }

    @Override
    public FlightSchedule addFlightSchedule(FlightSchedule flightSchedule) {
        return this.flightScheduleRepository.saveAndFlush(flightSchedule);
    }

    @Override
    public void deleteFlightSchedule(Long id) {
        this.flightScheduleRepository.findById(id).orElseThrow(() -> new FlightScheduleNotFoundException("FlightSchedule with id " + id + " not found"));
        this.flightScheduleRepository.deleteById(id);
    }

    @Override
    public FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule) {
        this.flightScheduleRepository.findById(flightSchedule.getId()).orElseThrow(() -> new FlightScheduleNotFoundException("FlightSchedule with id " + flightSchedule.getId() + " not found"));
        return FlightSchedule.build(flightSchedule.getId(), flightSchedule);
    }
}
