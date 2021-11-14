package vl.data.collectionservice.service.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vl.data.collectionservice.entities.Comment;
import vl.data.collectionservice.repo.CommentRepository;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{


    private final CommentRepository commentRepository;


    @Override
    public List<Comment> getComments() {
        try {
            return commentRepository.findAll();
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Comment getComment(Long id) {
        try {
            return commentRepository.findById(id).orElse(null);
        }catch (Exception e) {
            log.error("Exception: {}",e.getMessage());
            return null;
        }
    }

}
