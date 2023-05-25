package temesgen.girmay.microservice.masterdata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.domain.FlightSchedule;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleDTO;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class FlightScheduleServiceTest {

    @Mock
    private FlightScheduleRepository flightScheduleRepository;

    @Mock
    private AirportService airportService;

    @Mock
    private FlightScheduleEventPub flightScheduleEventPub;

    private FlightScheduleService flightScheduleService;

    @BeforeEach
    public void setUp(){
        flightScheduleService = new FlightScheduleServiceImpl(
                flightScheduleRepository,
                airportService,
                flightScheduleEventPub);
    }


    @Test
    void getFlightSchedules() {
        //given
        LocalTime departureTime1 = LocalTime.of(11, 45,0 );
        LocalTime arrivalTime1 = LocalTime.of(5, 18,0 );
        LocalTime departureTime2 = LocalTime.of(7, 32,0 );
        LocalTime arrivalTime2 = LocalTime.of(2, 50,0 );
        Airport airport1 = new Airport(1L, "DIA", "Doha International Airport ", "Doha");
        Airport airport2 = new Airport(2L, "DXB", "Dubai International Airport", "Dubai");
        Airport airport3 = new Airport(3L, "BOM", "Chhatrapati Shivaji Maharaj International Airport", "Mumbai");
        Airport airport4 = new Airport(4L, "JFK", "John F. Kennedy International Airport", "New York");
        FlightSchedule flightSchedule1 = new FlightSchedule(1L, "501", airport1, airport2, departureTime1, arrivalTime1);
        FlightSchedule flightSchedule2 = new FlightSchedule(2L, "507", airport3, airport4, departureTime2, arrivalTime2);
        List<FlightSchedule> flightScheduleList = new ArrayList<>();
        Collections.addAll(flightScheduleList, flightSchedule1, flightSchedule2);
        given(this.flightScheduleRepository.findAll()).willReturn(flightScheduleList);

        //when
        List<FlightSchedule> flightSchedules = this.flightScheduleService.getFlightSchedules();

        //then
        then(flightScheduleList).isEqualTo(flightSchedules);
    }

    @Test
    void addFlightSchedule() {
        //given
        LocalTime departureTime = LocalTime.of(11, 45,0 );
        LocalTime arrivalTime = LocalTime.of(5, 18,0 );
        Airport departurAirport = new Airport(1L, "DXB", "Dubai International Airport", "Dubai");
        Airport arrivalAirport = new Airport(2L, "DIA", "Doha International Airport", "Doha");
        FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO("519",
                "DIA", "DXB", departureTime, arrivalTime);
        FlightSchedule flightSchedule = new FlightSchedule(1L, "519", departurAirport, arrivalAirport, departureTime, arrivalTime);
        given(flightScheduleRepository.saveAndFlush(any())).willReturn(flightSchedule);

        //when
        FlightSchedule savedFlightSchedule = flightScheduleService.addFlightSchedule(flightScheduleDTO);

        //then
        Assertions.assertNotNull(savedFlightSchedule);
        Assertions.assertNotNull(savedFlightSchedule.getId());
        Assertions.assertEquals(savedFlightSchedule.getDepartureAirport().getAirportCode(), "DXB" );
        Assertions.assertEquals(savedFlightSchedule.getArrivalAirport().getAirportCode(), "DIA");
        Assertions.assertEquals(savedFlightSchedule.getFlightNumber(), "519");
    }

}