package vl.data.collectionservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Post;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBean {


    private Long postId;

    private Long id;

    private String name;

    private String email;

    private String body;
}
