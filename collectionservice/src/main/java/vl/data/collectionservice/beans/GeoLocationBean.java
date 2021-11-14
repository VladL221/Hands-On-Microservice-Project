package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.Address;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationBean {

    private String lat;

    private String lng;


}
