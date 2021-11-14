package vl.analysis.service.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vl.analysis.service.beans.user.AppUser;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {

    private Long userId;

    private Long id;

    private String title;

    private List<Photo> photos;

}
