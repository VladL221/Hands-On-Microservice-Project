package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Photo;
import vl.data.collectionservice.entities.user.AppUser;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumBean {


    private Long userId;

    private Long id;

    private String title;

    private List<PhotoBean> photos;

}
