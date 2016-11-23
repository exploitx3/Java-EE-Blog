package bg.softuni.web.beans.admin;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.TagModel;
import bg.softuni.service.PostServiceLocal;
import bg.softuni.service.TagServiceLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@ManagedBean(name = "tagManagementBean")
@ViewScoped
public class TagsManagementBean {
    @EJB
    PostServiceLocal postService;

    @EJB
    TagServiceLocal tagService;

    @Inject
    private HttpServletRequest request;

    private HashMap<String, TagModel> allTags = new HashMap<String, TagModel>();

    private List<PostModel> topThreePopularPosts;

    @PostConstruct
    public void init() {
        List<TagModel> distinctTags = tagService.getAllTags();
        for (TagModel distinctTag : distinctTags) {
            this.allTags.putIfAbsent(distinctTag.getName(), distinctTag);
        }

        this.topThreePopularPosts = postService.getTopThreePopularPosts();
    }

    public HashMap<String, TagModel> getAllTags() {
        return allTags;
    }

    public void setAllTags(HashMap<String, TagModel> allTags) {
        this.allTags = allTags;
    }

    public String deleteTag(TagModel tag) {
//        TagModel tag = (TagModel) e.getComponent().getAttributes().get("tag");
        this.tagService.deleteTagById(tag.getId());
        return "";
    }
}