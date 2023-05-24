package temesgen.girmay.microservice.connectionbuilder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temesgen.girmay.microservice.connectionbuilder.domain.Airport;
import temesgen.girmay.microservice.connectionbuilder.service.AirportService;

import java.util.List;

@RestController
@RequestMapping("/connection-builder-airports")
public class AirportApiResources {

    private final AirportService airportService;

    public AirportApiResources(final AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAirports() {
        return ResponseEntity.ok(this.airportService.getAirports());
    }

}
