package vl.analysis.service.beans.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {


    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private GeoLocation geo;


}
