package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.AppUser;
import vl.data.collectionservice.entities.user.GeoLocation;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBean {


    private String street;

    private String suite;

    private String city;

    private String zipcode;

    private GeoLocationBean geo;

}
