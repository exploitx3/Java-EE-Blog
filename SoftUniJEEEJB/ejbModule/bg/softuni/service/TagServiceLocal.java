package bg.softuni.service;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;

import javax.ejb.Local;
import java.util.List;
import java.util.Set;

@Local
public interface TagServiceLocal {
    TagModel save(TagModel entity);

    TagModel update(TagModel entity);

    void delete(TagModel entity);

    void deleteTagById(Long id);

    List<TagModel> getAllTags();

    List<PostModel> findTagPostsByTagName(String name);

    List<PostModel> searchPostsByTagName(String name);

    List<PostModel> findPostsByTagId(Long id);

    List<TagModel> findTagsForPostByPostId(Long id);

    TagModel getTagById(Long id);
}
