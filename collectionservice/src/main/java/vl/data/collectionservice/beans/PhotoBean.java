package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Album;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoBean {

    private Long albumId;

    private Long id;

    private String title;

    private String url;

    private String thumbnailUrl;

}
