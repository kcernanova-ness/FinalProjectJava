package sk.ness.academy.service;

import sk.ness.academy.domain.Comment;

import java.util.List;

public interface CommentService {

    /** Returns all available {@link Comment}s */
    List<Comment> findAll();

    /** Returns {@link Comment} with provided ID */
    Comment findByID(Integer commentId);

    /** Creates new {@link Comment} */
    void createComment(Comment comment);

    /** Delete {@link Comment} with provided ID */
    void deleteByID(Integer commentId);
}
