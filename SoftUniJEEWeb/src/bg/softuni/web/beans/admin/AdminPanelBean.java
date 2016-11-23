package bg.softuni.web.beans.admin;

import bg.softuni.entity.UserModel;
import bg.softuni.service.UserServiceLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "adminPanelBean")
@ViewScoped
public class AdminPanelBean {
	@EJB
	UserServiceLocal userService;

	@PostConstruct
	public void init() {
	}

	public String editUserAction() {
		return "/blog/admin/edit-user";
	}

	public String createUserAction() {
		return "/blog/admin/create-user?faces-redirect=true";
	}

	public String manageTagsAction() {
		return "/blog/admin/manage-tags?faces-redirect=true";
	}

	public List<UserModel> getAllUsers() {
		return userService.getAllUsersforDisplay();
	}

}
