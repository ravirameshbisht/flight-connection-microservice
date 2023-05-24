package temesgen.girmay.microservice.connectionbuilder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temesgen.girmay.microservice.connectionbuilder.domain.Airport;
import temesgen.girmay.microservice.connectionbuilder.domain.AirportCodesDTO;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightConnectionDTO;
import temesgen.girmay.microservice.connectionbuilder.service.AirportService;
import temesgen.girmay.microservice.connectionbuilder.service.FlightConnectionService;

import java.util.List;

@RestController
@RequestMapping("/connection-builder")
class ConnectionBuilderApiResources {

    private final FlightConnectionService flightConnectionService;

    public ConnectionBuilderApiResources(final FlightConnectionService flightConnectionService) {
        this.flightConnectionService = flightConnectionService;
    }

    @PostMapping("/connection-flights")
    public List<FlightConnectionDTO> buildFlightConnections(@RequestBody AirportCodesDTO airportCodesDTO) {
        return this.flightConnectionService.buildFlightConnections(airportCodesDTO);
    }
}
