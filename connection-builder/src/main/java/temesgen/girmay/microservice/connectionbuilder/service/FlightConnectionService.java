package temesgen.girmay.microservice.connectionbuilder.service;

import temesgen.girmay.microservice.connectionbuilder.domain.AirportCodesDTO;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightConnectionDTO;

import java.util.List;

public interface FlightConnectionService {

    List<FlightConnectionDTO> buildFlightConnections(AirportCodesDTO airportCodesDTO);
}
