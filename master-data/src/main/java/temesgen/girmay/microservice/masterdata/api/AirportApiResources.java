package temesgen.girmay.microservice.masterdata.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.service.AirportService;

import java.util.List;

@RestController
@RequestMapping("/master-data")
public class AirportApiResources {

    private final AirportService airportService;

    public AirportApiResources(final AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping("/airports")
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport) {
        return ResponseEntity.ok(this.airportService.addAirport(airport));
    }

    @GetMapping("/airports/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(this.airportService.findAirportById(id));
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> getAirports() {
        return ResponseEntity.ok(this.airportService.getAirports());
    }

    @DeleteMapping("/airports/{id}")
    public ResponseEntity<Void> deleteAirportById(@PathVariable Long id) {
        this.airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/airports/{id}")
    public ResponseEntity<Airport> updateAirportById(@PathVariable Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(this.airportService.updateAirport(airport));
    }

}
