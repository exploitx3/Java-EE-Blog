package bg.softuni.service;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.PostModel;
import bg.softuni.entity.PostTagModel;
import bg.softuni.entity.TagModel;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless(name = "PostServiceEJB")
public class PostService implements PostServiceLocal {

    @PersistenceContext
    protected EntityManager entityManager;

    @EJB
    private TagServiceLocal tagService;

    public PostService() {
    }

    @Override
    public List<PostModel> findAllPosts() {
        String query = "select post from PostModel post order by upper(post.title) asc";
        Query q = entityManager.createQuery(query);

        return q.getResultList();
    }

    @Override
    public PostModel save(PostModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public PostModel update(PostModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(PostModel entity) {
        entityManager.remove(entity);
    }

    @Override
    public PostModel findById(Long id) {
        try {
            PostModel instance = entityManager.find(PostModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public void incrementPostViewsByPostId(Long id) {
        try {
            PostModel instance = entityManager.find(PostModel.class, id);
            instance.incrementVisits();
            entityManager.merge(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            PostModel instance = entityManager.find(PostModel.class, id);

            entityManager.remove(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public List<PostModel> getTopThreePopularPosts(){
        String query = "select post from PostModel post order by post.visits desc";
        Query q = entityManager.createQuery(query);
        q.setMaxResults(3);
        
        return q.getResultList();
    }
}
