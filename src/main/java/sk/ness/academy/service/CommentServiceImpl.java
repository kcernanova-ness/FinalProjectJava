package sk.ness.academy.service;

import org.springframework.stereotype.Service;
import sk.ness.academy.dao.CommentDAO;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDAO commentDAO;

    @Override
    public List<Comment> findAll() {
        return this.commentDAO.findAll();
    }

    @Override
    public Comment findByID(Integer commentId) {
        return this.commentDAO.findByID(commentId);
    }

    @Override
    public void createComment(Comment comment) { this.commentDAO.persist(comment); }

    @Override
    public void deleteByID(Integer commentId) {
        this.commentDAO.deleteByID(commentId);
    }
}
