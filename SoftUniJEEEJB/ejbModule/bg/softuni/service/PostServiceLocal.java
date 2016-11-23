package bg.softuni.service;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.PostModel;

@Local
public interface PostServiceLocal {
    List<PostModel> findAllPosts();

    PostModel save(PostModel entity);

    PostModel update(PostModel entity);

    void delete(PostModel entity);

    PostModel findById(Long id);

    void deleteById(Long id);

    void incrementPostViewsByPostId(Long id);

    List<PostModel> getTopThreePopularPosts();
}
