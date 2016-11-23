package bg.softuni.web.beans.tags;

import bg.softuni.entity.CommentModel;
import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;
import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.CommentServiceLocal;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.TagServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "tagBean")
@ViewScoped
public class TagBean {
    @Inject
    HttpServletRequest request;

    @EJB
    TagServiceLocal tagService;

    private TagModel tag;

    public List<PostModel> posts;

    private UserModel user;

    @PostConstruct
    public void init() {
        this.user = GeneralUtils.getLoggedUser(request);

        String id = request.getParameter("id");

        if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
            this.tag = tagService.getTagById(Long.valueOf(id));
            if(this.tag != null){
                this.posts = tagService.findTagPostsByTagName(this.tag.getName());
            }
        }
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public TagModel getTag() {
        return tag;
    }

    public void setTag(TagModel tag) {
        this.tag = tag;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

}
