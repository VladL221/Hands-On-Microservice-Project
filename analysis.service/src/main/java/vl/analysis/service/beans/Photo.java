package vl.analysis.service.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    private Long albumId;

    private Long id;

    private String title;

    private String url;

    private String thumbnailUrl;

}
