package sk.ness.academy.dao;

import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import sk.ness.academy.domain.Comment;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository
public class CommentHibernateDAO implements CommentDAO{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Comment findByID(Integer commentId) {
        try{
            return this.sessionFactory.getCurrentSession().get(Comment.class, commentId);
        }catch(NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Comment> findAll() { return this.sessionFactory.getCurrentSession().createSQLQuery("select * from comments").addEntity(Comment.class).list(); }

    @Override
    public void persist(Comment comment) { this.sessionFactory.getCurrentSession().saveOrUpdate(comment); }

    @Override
    public void deleteByID(Integer commentId) {
        try{
            Comment comment = this.sessionFactory.getCurrentSession().load(Comment.class, commentId);
            this.sessionFactory.getCurrentSession().delete(comment);
            this.sessionFactory.getCurrentSession().flush();
        }catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
