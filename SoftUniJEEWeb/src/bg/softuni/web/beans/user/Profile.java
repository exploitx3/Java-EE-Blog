package bg.softuni.web.beans.user;

import bg.softuni.entity.UserModel;
import bg.softuni.service.UserServiceLocal;
import org.apache.http.HttpRequest;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ManagedBean(name = "profile")
@ViewScoped
public class Profile {
    @Inject
    HttpServletRequest request;

    @EJB
    UserServiceLocal userService;

    @PostConstruct
    public void init() {
    }

    public String getCreatePostsAction() {
        return "/blog/user/create-post";
    }

    public String getEditProfileAction() {
        return "/blog/user/edit-profile";
    }

}
