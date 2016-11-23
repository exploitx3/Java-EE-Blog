package bg.softuni.service;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Stateless(name = "TagServiceEJB")
public class TagService implements TagServiceLocal {

    @PersistenceContext
    protected EntityManager entityManager;

    public TagService() {
    }

    @Override
    public TagModel save(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public void delete(TagModel entity) {
        entityManager.remove(entity);
    }

    @Override
    public List<TagModel> getAllTags() {
        String query = "select tag from TagModel tag";
        Query q = entityManager.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<PostModel> findTagPostsByTagName(String name) {
        String query = "select tag.posts from TagModel tag where tag.name = :name";
        Query q = entityManager.createQuery(query);
        q.setParameter("name", name);

        return q.getResultList();
    }

    @Override
    public List<PostModel> searchPostsByTagName(String name) {
        String query = "select tag.posts from TagModel tag where lower(tag.name) like lower(:name)";
        Query q = entityManager.createQuery(query);
        q.setParameter("name", "%" + name + "%");

        return q.getResultList();
    }

    @Override
    public TagModel getTagById(Long id) {
        try {
            TagModel instance = entityManager.find(TagModel.class, id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }

    @Override
    public List<PostModel> findPostsByTagId(Long id) {
        String query = "select tag.posts from TagModel tag where tag.id = :id";
        Query q = entityManager.createQuery(query);
        q.setParameter("id", id);

        return q.getResultList();
    }

    @Override
    public List<TagModel> findTagsForPostByPostId(Long id) {
        String query = "select post.tags from PostModel post where post.id = :id";
        Query q = entityManager.createQuery(query);
        q.setParameter("id", id);

        return q.getResultList();
    }

    @Override
    public void deleteTagById(Long id) {
        try {
            TagModel instance = entityManager.find(TagModel.class, id);
            entityManager.remove(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }
}
