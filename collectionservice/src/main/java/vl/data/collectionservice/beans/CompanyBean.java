package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.AppUser;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyBean {


    private String name;

    private String catchPhrase;

    private String bs;

}
