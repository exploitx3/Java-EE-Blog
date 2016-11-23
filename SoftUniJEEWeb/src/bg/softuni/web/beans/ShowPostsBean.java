package bg.softuni.web.beans;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.UserServiceLocal;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ManagedBean(name = "showPostsBean")
@RequestScoped
public class ShowPostsBean {
    @EJB
    PostServiceLocal postService;

    @Inject
    private HttpServletRequest request;

    @PostConstruct
    public void init() {
    }

    public List<PostModel> getAllPosts() {
        return postService.findAllPosts();
    }
}
