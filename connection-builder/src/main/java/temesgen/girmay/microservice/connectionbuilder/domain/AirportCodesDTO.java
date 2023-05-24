package temesgen.girmay.microservice.connectionbuilder.domain;

public class AirportCodesDTO {
    private String departureAirportCode;
    private String arrivalAirportCode;

    public AirportCodesDTO(String departureAirportCode, String arrivalAirportCode) {
        this.departureAirportCode = departureAirportCode;
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getDepartureAirportCode(){
        return this.departureAirportCode;
    }

    public String getArrivalAirportCode(){
        return this.arrivalAirportCode;
    }
}
