package vl.analysis.service.beans.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import vl.analysis.service.beans.Album;
import vl.analysis.service.beans.Post;
import vl.analysis.service.beans.Todo;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    private Company company;

    private Address address;

    private List<Todo> todos;

    private List<Post> posts;

    private List<Album> albums;


}
