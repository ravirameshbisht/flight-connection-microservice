package temesgen.girmay.microservice.masterdata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.domain.AirportRepository;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleRepository;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;
    @Mock
    private FlightScheduleRepository flightScheduleRepository;

    @Mock
    private AirportEventPub airportEventPub;

    private AirportService airportService;

    @BeforeEach
    public void setUp(){
        airportService = new AirportServiceImpl(airportRepository,
                flightScheduleRepository,
                airportEventPub);
    }
    @Test
    void findAirportByIdTest() {
        //given
        Long airportId = 1L;
        Airport givenAirport = new Airport("ADD", "Bole International Airport", "Addis Ababa");
        given(airportRepository.findById(airportId)).willReturn(Optional.of(givenAirport));

        //when
        Airport expectedAirport = this.airportService.findAirportById(airportId);

        //then
        then(expectedAirport).isEqualTo(givenAirport);

    }

    @Test
    void findAirportByAirportCode() {
        //given
        String airportCode = "ADD" ;
        Airport expectedAirport = new Airport(1L, airportCode, "Bole International Airport", "Addis Ababa");
        given(airportRepository.findByAirportCode(airportCode)).willReturn(expectedAirport);

        //when
        Airport actualAirport = airportService.findAirportByAirportCode(airportCode);

        //then
        then(actualAirport).isEqualTo(expectedAirport);
    }

    @Test
    void addAirportTest(){
        //given
        Airport newAirport = new Airport("DXB", "Dubai International Airport", "Dubai");
        Airport AIRPORT_RECORD_1 = new Airport(2L, "DXB", "Dubai International Airport", "Dubai");

        //when
        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(AIRPORT_RECORD_1);
        Airport savedAirport = airportService.addAirport(newAirport);

        //then
        Assertions.assertNotNull(savedAirport);
        Assertions.assertNotNull(savedAirport.getId());
        Assertions.assertEquals(savedAirport.getAirportCode(), "DXB" );
        Assertions.assertEquals(savedAirport.getAirportName(), "Dubai International Airport");
        Assertions.assertEquals(savedAirport.getCityName(), "Dubai");
    }
}