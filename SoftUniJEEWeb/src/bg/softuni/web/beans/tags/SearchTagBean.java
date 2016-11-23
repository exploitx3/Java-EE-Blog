package bg.softuni.web.beans.tags;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;
import bg.softuni.entity.UserModel;
import bg.softuni.service.TagServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@ManagedBean(name = "searchTagBean")
@ViewScoped
public class SearchTagBean {
    @Inject
    HttpServletRequest request;

    @EJB
    TagServiceLocal tagService;

    private Set<PostModel> searchedPosts = new HashSet<PostModel>();

    private UserModel user;

    @PostConstruct
    public void init() {
        this.user = GeneralUtils.getLoggedUser(request);

        String name = request.getParameter("name");

        if (StringUtils.isNotBlank(name)) {
            List<PostModel> uniquePosts = this.tagService.searchPostsByTagName(name);
            this.searchedPosts.addAll(uniquePosts);
        }
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<PostModel> getSearchedPosts() {
        return searchedPosts;
    }

    public void setSearchedPosts(Set<PostModel> searchedPosts) {
        this.searchedPosts = searchedPosts;
    }
}
