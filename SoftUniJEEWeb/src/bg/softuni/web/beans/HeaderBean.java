package bg.softuni.web.beans;

import bg.softuni.entity.PostModel;
import bg.softuni.entity.UserModel;
import bg.softuni.service.TagServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "headerBean")
@ApplicationScoped
public class HeaderBean {
    @Inject
    HttpServletRequest request;

    private String searchText;

    public String logout(){
        request.getSession().invalidate();
        return "/blog/home.html?faces-redirect=true";
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String search() {
        return "/blog/tags/search.html" + "?faces-redirect=true" + "name=" + this.searchText ;
    }
}
