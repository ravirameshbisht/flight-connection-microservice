package temesgen.girmay.microservice.connectionbuilder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temesgen.girmay.microservice.connectionbuilder.domain.*;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.*;

@ExtendWith(MockitoExtension.class)
class FlightConnectionServiceTest {

    @Mock
    private FlightScheduleRepository flightScheduleRepository;

    @Mock
    private AirportRepository airportRepository;

    private FlightConnectionService flightConnectionService;

    @BeforeEach
    public void setUp(){
        flightConnectionService = new FlightConnectionServiceImpl(flightScheduleRepository, airportRepository);
    }

    @Test
    public void validFlightConnectionsTest() throws Exception {
        //given

        LocalTime onwardDepartureTime = LocalTime.of(11, 45,0 );
        LocalTime onwardArrivalTime = LocalTime.of(13, 18,0 );
        LocalTime connectionDepartureTime = LocalTime.of(16, 32,0 );
        LocalTime connectionArrivalTime = LocalTime.of(21, 50,0 );

        Airport onwardDepartureAirport = new Airport(1L, "BOM", "Mumbai International Airport ", "Mumbai");
        Airport onwardArrivalAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionDepartureAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionArrivalAirport = new Airport(3L, "FRA", "Frankfurt Airport", "Frankfurt");

        List<FlightSchedule> departureFlightSchedules = List.of(new FlightSchedule(1L, "321", onwardDepartureAirport, onwardArrivalAirport, onwardDepartureTime, onwardArrivalTime));
        List<FlightSchedule> arrivalFlightSchedules = List.of(new FlightSchedule(2L, "235", connectionDepartureAirport, connectionArrivalAirport, connectionDepartureTime, connectionArrivalTime));

        AirportCodesDTO airportCodesDTO = new AirportCodesDTO("BOM", "FRA");

        given(flightScheduleRepository.findDepartureFlightSchedules(airportCodesDTO.getDepartureAirportCode())).willReturn(departureFlightSchedules);
        given(flightScheduleRepository.findArrivalFlightSchedules(airportCodesDTO.getArrivalAirportCode())).willReturn(arrivalFlightSchedules);


        //when
        List<FlightConnectionDTO> returnedFlightConnectionDTOList = flightConnectionService.buildFlightConnections(airportCodesDTO);

        //then
        then(returnedFlightConnectionDTOList).isNotEmpty();
        then(returnedFlightConnectionDTOList.get(0).getOnwardDepartureAirportCode()).isEqualTo("BOM");
        then(returnedFlightConnectionDTOList.get(0).getConnectionArrivalAirportCode()).isEqualTo("FRA");

    }

    @Test
    public void minimumWaitingTimeAtTheAirportShouldBeTwoHours() throws Exception {
        //given

        LocalTime onwardDepartureTime = LocalTime.of(11, 45,0 );
        LocalTime onwardArrivalTime = LocalTime.of(13, 18,0 );
        LocalTime connectionDepartureTime = LocalTime.of(14, 32,0 );
        LocalTime connectionArrivalTime = LocalTime.of(21, 50,0 );

        Airport onwardDepartureAirport = new Airport(1L, "BOM", "Mumbai International Airport ", "Mumbai");
        Airport onwardArrivalAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionDepartureAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionArrivalAirport = new Airport(3L, "FRA", "Frankfurt Airport", "Frankfurt");

        List<FlightSchedule> departureFlightSchedules = List.of(new FlightSchedule(1L, "321", onwardDepartureAirport, onwardArrivalAirport, onwardDepartureTime, onwardArrivalTime));
        List<FlightSchedule> arrivalFlightSchedules = List.of(new FlightSchedule(2L, "235", connectionDepartureAirport, connectionArrivalAirport, connectionDepartureTime, connectionArrivalTime));

        AirportCodesDTO airportCodesDTO = new AirportCodesDTO("BOM", "FRA");

        given(flightScheduleRepository.findDepartureFlightSchedules(airportCodesDTO.getDepartureAirportCode())).willReturn(departureFlightSchedules);
        given(flightScheduleRepository.findArrivalFlightSchedules(airportCodesDTO.getArrivalAirportCode())).willReturn(arrivalFlightSchedules);


        //when
        List<FlightConnectionDTO> returnedFlightConnectionDTOList = flightConnectionService.buildFlightConnections(airportCodesDTO);

        //then
        then(returnedFlightConnectionDTOList).isEmpty();
    }

    @Test
    public void maximumWaitingTimeAtTheAirportShouldBeEightHours() throws Exception {
        //given

        LocalTime onwardDepartureTime = LocalTime.of(3, 45,0 );
        LocalTime onwardArrivalTime = LocalTime.of(7, 18,0 );
        LocalTime connectionDepartureTime = LocalTime.of(16, 32,0 );
        LocalTime connectionArrivalTime = LocalTime.of(21, 50,0 );

        Airport onwardDepartureAirport = new Airport(1L, "BOM", "Mumbai International Airport ", "Mumbai");
        Airport onwardArrivalAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionDepartureAirport = new Airport(2L, "LHR", "Heathrow Airport", "London");
        Airport connectionArrivalAirport = new Airport(3L, "FRA", "Frankfurt Airport", "Frankfurt");

        List<FlightSchedule> departureFlightSchedules = List.of(new FlightSchedule(1L, "321", onwardDepartureAirport, onwardArrivalAirport, onwardDepartureTime, onwardArrivalTime));
        List<FlightSchedule> arrivalFlightSchedules = List.of(new FlightSchedule(2L, "235", connectionDepartureAirport, connectionArrivalAirport, connectionDepartureTime, connectionArrivalTime));

        AirportCodesDTO airportCodesDTO = new AirportCodesDTO("BOM", "FRA");

        given(flightScheduleRepository.findDepartureFlightSchedules(airportCodesDTO.getDepartureAirportCode())).willReturn(departureFlightSchedules);
        given(flightScheduleRepository.findArrivalFlightSchedules(airportCodesDTO.getArrivalAirportCode())).willReturn(arrivalFlightSchedules);


        //when
        List<FlightConnectionDTO> returnedFlightConnectionDTOList = flightConnectionService.buildFlightConnections(airportCodesDTO);

        //then
        then(returnedFlightConnectionDTOList).isEmpty();

    }

}