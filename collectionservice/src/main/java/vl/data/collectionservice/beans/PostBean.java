package vl.data.collectionservice.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vl.data.collectionservice.entities.Comment;
import vl.data.collectionservice.entities.user.AppUser;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostBean {


    private Long userId;

    private Long id;

    private String title;

    private String body;

    private List<CommentBean> comments;

}
