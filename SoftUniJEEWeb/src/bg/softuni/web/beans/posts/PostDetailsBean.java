package bg.softuni.web.beans.posts;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.PostModel;
import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.CommentServiceLocal;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

@ManagedBean(name = "postDetailsBean")
@ViewScoped
public class PostDetailsBean {
    @Inject
    HttpServletRequest request;

    @EJB
    PostServiceLocal postService;

    @EJB
    CommentServiceLocal commentService;

    private PostModel post;

    private UserModel user;

    private List<CommentModel> comments;

    private HashMap<Long, CommentModel> commentsMap = new HashMap<Long, CommentModel>();

    private CommentModel newComment;

    @PostConstruct
    public void init() {
        this.user = GeneralUtils.getLoggedUser(request);

        String id = request.getParameter("id");

        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            this.post = postService.findById(Long.valueOf(id));
            postService.incrementPostViewsByPostId(post.getId());
            this.comments = commentService.findCommentsForPostById(Long.valueOf(id));
            for (int i = 0; i < comments.size(); i++) {
                commentsMap.put(comments.get(i).getId(), comments.get(i));
            }

            if (user != null) {
                this.newComment = new CommentModel();
                this.newComment.setAuthor(this.user);
                this.newComment.setDate(new Date());
                this.newComment.setPost(this.post);
            }
        }
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Boolean couldAddComment() {
        return this.user != null;
    }

    public Boolean couldDeleteComment(Long commentId) {
        if (this.user != null) {
            return (this.user.getType() == UserTypes.Administrator || (this.commentsMap.get(commentId).getAuthor().getId().equals(this.user.getId())));
        }
        return false;
    }

    public void deleteComment(ActionEvent event) {
        CommentModel commentForRemoval = (CommentModel) event.getComponent().getAttributes().get("comment");
        commentService.deleteById(commentForRemoval.getId());
        RequestContext.getCurrentInstance().execute("window.location.reload()");
    }

    public String addComment() {
        if (StringUtils.isNotBlank(this.newComment.getTitle())
                && StringUtils.isNotBlank(this.newComment.getTitle())) {
            this.commentService.save(this.newComment);

            this.newComment = new CommentModel();
            this.newComment.setAuthor(this.user);
            this.newComment.setDate(new Date());
            this.newComment.setPost(this.post);

            RequestContext.getCurrentInstance().execute("window.location.reload()");

            return "";
        } else {
            MessageUtils.addErrorMessage("login.error.invalid.credentials");

            return "";
        }
    }

    public CommentModel getNewComment() {
        return newComment;
    }

    public void setNewComment(CommentModel newComment) {
        this.newComment = newComment;
    }

    public HashMap<Long, CommentModel> getCommentsMap() {
        return commentsMap;
    }

    public void setCommentsMap(HashMap<Long, CommentModel> commentsMap) {
        this.commentsMap = commentsMap;
    }

    public Boolean shouldShowPostButtons() {
        if (this.user != null) {
            return (this.user.getType() == UserTypes.Administrator) || this.post.getAuthor().getUsername().equals(this.user.getUsername());
        } else {
            return false;
        }
    }

    public String deletePost() {
        postService.deleteById(this.post.getId());
        MessageUtils.addMessage("post.deleted.successfully");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        return "/blog/posts?faces-redirect=true";
    }

    public String editPost() {
        postService.update(this.post);
        MessageUtils.addMessage("post.edited.successfully");
        RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
        return "";
    }
}
