package bg.softuni.web.beans.posts;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;
import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.TagService;
import bg.softuni.service.TagServiceLocal;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

@ManagedBean(name = "createPostBean")
@ViewScoped
public class CreatePostBean {

    @Inject
    HttpServletRequest request;

    @EJB
    PostServiceLocal postService;

    @EJB
    TagServiceLocal tagService;

    private PostModel post;

    private List<String> tags = new ArrayList<String>();

    private String tagName;

    @PostConstruct
    public void init() {
        post = new PostModel();
        post.setDate(new Date());
        post.setAuthor((UserModel) request.getSession().getAttribute("LOGGED_USER"));
    }

    public String createAction() {
        if (!validate()) {
            return null;
        }

        HashSet<TagModel> tagModelsList = new HashSet<TagModel>();
        for (String tag : this.tags) {
            TagModel tagToBeAppended = new TagModel();
            tagToBeAppended.setName(tag);
            tagModelsList.add(tagToBeAppended);
        }

        this.post.setTags(tagModelsList);
        postService.save(this.post);


        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);

        return "/blog/posts.html?faces-redirect=true";
    }

    public String addTag() {
        if(this.tags.contains(this.tagName)){
            MessageUtils.addErrorMessage("error.invalid.tagDuplicate");
            return "";
        }

        if(this.tagName.length() < 2){
            MessageUtils.addErrorMessage("error.invalid.tagLength");
            return "";
        }
        this.tags.add(this.tagName);
        return "";
    }

    public PostModel getPost() {
        return post;
    }

    public void setPost(PostModel post) {
        this.post = post;
    }

    protected boolean validate() {
        boolean hasErrors = false;
        if (StringUtils.isBlank(post.getTitle())) {
            MessageUtils.addErrorMessage("error.required.username");
            hasErrors = true;
        }

        if (StringUtils.isBlank(post.getContent())) {
            MessageUtils.addErrorMessage("code_error_required");
            hasErrors = true;
        }

        if (hasErrors) {
            return false;
        }

        return true;
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
