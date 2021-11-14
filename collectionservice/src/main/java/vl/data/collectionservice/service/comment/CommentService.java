package vl.data.collectionservice.service.comment;

import vl.data.collectionservice.entities.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();
    Comment getComment(Long id);



}
