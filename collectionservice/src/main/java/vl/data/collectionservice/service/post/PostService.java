package vl.data.collectionservice.service.post;

import vl.data.collectionservice.beans.PostBean;
import vl.data.collectionservice.entities.Post;

import java.util.List;

public interface PostService {

    List<PostBean> getPosts();
    Post getPost(Long id);
    Post createPost(Post post);
    Post updatePost(Post post);

}
