package vl.analysis.service.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vl.analysis.service.beans.user.AppUser;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private Long userId;

    private Long id;

    private String title;

    private String body;

    private List<Comment> comments;

}
