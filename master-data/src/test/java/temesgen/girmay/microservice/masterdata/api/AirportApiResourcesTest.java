package temesgen.girmay.microservice.masterdata.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.service.AirportService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(AirportApiResources.class)
@TestPropertySource("classpath:/test.properties")
class AirportApiResourcesTest {

    @MockBean
    private AirportService airportService;

    @Autowired
    MockMvc mvc;

    @Autowired
    private JacksonTester<Airport> airportJson;

    @Autowired
    JacksonTester<List<Airport>> airportListJson;

    @Test
    void getAirportsTest() throws Exception {
        //given
        Airport airport1 = new Airport(1L, "DIA", "Doha International Airport ", "Doha");
        Airport airport2 = new Airport(2L, "DXB", "Dubai International Airport", "Dubai");
        List<Airport> airportList = new ArrayList<>();
        Collections.addAll(airportList, airport1, airport2);
        given(this.airportService.getAirports()).willReturn(airportList);

        //when
        MockHttpServletResponse response = mvc.perform(get("/master-data/airports").accept(MediaType.APPLICATION_JSON))
                                                .andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString())
                .isEqualTo(airportListJson.write(airportList).getJson());

    }

    @Test
    void createAirportTest() throws Exception {
        //given
        Airport airport = new Airport("LHR", "Heathrow Airport", "London");
        Airport expectedResponse = new Airport(1L, "LHR", "Heathrow Airport", "London");
        given(this.airportService.addAirport(airport)).willReturn(expectedResponse);

        //when
        MockHttpServletResponse response = mvc
                .perform(post("/master-data/airports")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.airportJson.write(airport).getJson()))
                .andReturn().getResponse();

        //then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                airportJson.write(
                        expectedResponse
                ).getJson());
    }

}