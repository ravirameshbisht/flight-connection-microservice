package temesgen.girmay.microservice.masterdata.service;

import temesgen.girmay.microservice.masterdata.domain.FlightSchedule;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleDTO;

import java.util.List;

public interface FlightScheduleService {

    FlightSchedule getFlightScheduleById(Long id);
    List<FlightSchedule> getFlightSchedules();
    FlightSchedule addFlightSchedule(FlightScheduleDTO flightScheduleDTO);
    void deleteFlightSchedule(Long id);
    FlightSchedule updateFlightSchedule(FlightSchedule flightSchedule);
}
