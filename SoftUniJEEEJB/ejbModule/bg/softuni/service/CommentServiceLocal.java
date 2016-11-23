package bg.softuni.service;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.CommentModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CommentServiceLocal {
    CommentModel save(CommentModel entity);

    CommentModel update(CommentModel entity);

    void delete(CommentModel entity);

    void deleteById(Long id);

    List<CommentModel> findCommentsForAuthorById(Long id);

    List<CommentModel> findCommentsForPostById(Long id);
}
