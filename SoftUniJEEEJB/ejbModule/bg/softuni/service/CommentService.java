package bg.softuni.service;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.PostModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "CommentServiceEJB")
public class CommentService implements CommentServiceLocal{

    @PersistenceContext
    protected EntityManager entityManager;

    public CommentService() {
    }

    @Override
    public CommentModel save(CommentModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public CommentModel update(CommentModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(CommentModel entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<CommentModel> findCommentsForAuthorById(Long id) {
        String query = "select comment from CommentModel comment where author.id = :id";
        Query q = entityManager.createQuery(query);
        q.setParameter("id", id);

        return q.getResultList();
    }

    @Override
    public List<CommentModel> findCommentsForPostById(Long id) {
        String query = "select comment from CommentModel comment where post.id = :id";
        Query q = entityManager.createQuery(query);
        q.setParameter("id", id);

        return q.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        try {
            CommentModel instance = entityManager.find(CommentModel.class, id);
            entityManager.remove(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
