package vl.data.collectionservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "albums")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private AppUser userId;

    @Id
    private Long id;

    private String title;

    @OneToMany(mappedBy = "albumId", targetEntity = Photo.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photos;


}
