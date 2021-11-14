package vl.data.collectionservice.entities.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Album;
import vl.data.collectionservice.entities.Comment;
import vl.data.collectionservice.entities.Post;
import vl.data.collectionservice.entities.Todo;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @Column(name = "id")
    private Long id;

    private String name;

    private String username;

    private String email;

    private String phone;

    private String website;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "userId", targetEntity = Todo.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos;

    @OneToMany(mappedBy = "userId", targetEntity = Post.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "userId", targetEntity = Album.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Album> albums;





}
