package temesgen.girmay.microservice.connectionbuilder.service;

import temesgen.girmay.microservice.connectionbuilder.domain.FlightSchedule;

import java.util.List;

public interface FlightScheduleService {

    List<FlightSchedule> findAllSchedules();

    FlightSchedule addFlightSchedule(FlightSchedule flightSchedule);
    void deleteFlightSchedule(Long id);
    FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule);
}
