package temesgen.girmay.microservice.masterdata.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {


    @Query("SELECT flightSchedule FROM FlightSchedule flightSchedule WHERE flightSchedule.departureAirport.airportCode = :departureAirportCode")
    List<FlightSchedule> findDepartureFlightSchedules(@Param("departureAirportCode") String departureAirportCode);

    @Query("SELECT flightSchedule FROM FlightSchedule flightSchedule WHERE flightSchedule.arrivalAirport.airportCode = :arrivalAirportCode")
    List<FlightSchedule> findArrivalFlightSchedules(@Param("arrivalAirportCode") String arrivalAirportCode);

    @Query("SELECT flightSchedule FROM FlightSchedule flightSchedule WHERE flightSchedule.arrivalAirport.id = :id OR flightSchedule.departureAirport.id = :id")
    List<FlightSchedule> findAllFlightSchedulesForAirport(@Param("id") Long id);
}
