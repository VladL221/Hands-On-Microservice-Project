package vl.data.collectionservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.user.AppUser;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private AppUser userId;

    @Id
    private Long id;

    private String title;

    private String body;

    @OneToMany(mappedBy = "postId", targetEntity = Comment.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;


}
