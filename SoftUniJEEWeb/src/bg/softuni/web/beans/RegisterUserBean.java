package bg.softuni.web.beans;

import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.regex.Pattern;

@ManagedBean(name = "registerUserBean")
@RequestScoped
public class RegisterUserBean {

	@Inject
	HttpServletRequest request;

	@EJB
	UserServiceLocal userService;

	private UserModel user;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
	private Pattern pattern;

	@PostConstruct
	public void init() {
		user = new UserModel();
	}

	public String createAction() {

		if (!validate()) {
			return null;
		}

		String cryptedPassword = GeneralUtils.encodeSha256Password(user.getPassword());
		user.setPassword(cryptedPassword);
		user.setType(UserTypes.NormalUser);

		userService.save(user);

		request.getSession().setAttribute("LOGGED_USER", user);

		return "/blog/user/profile?faces-redirect=true";
	}


	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(user.getUsername())) {
			MessageUtils.addErrorMessage("error.required.username");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getPassword())) {
			MessageUtils.addErrorMessage("error.required.password");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getFirstName())) {
			MessageUtils.addErrorMessage("error.required.firstname");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getLastName())) {
			MessageUtils.addErrorMessage("error.required.lastname");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getEmail())) {
			MessageUtils.addErrorMessage("error.required.email");
			hasErrors = true;
		}

		pattern = Pattern.compile(EMAIL_PATTERN);
		if (!pattern.matcher(user.getEmail()).matches()) {
			MessageUtils.addErrorMessage("error.invalid.email");
			hasErrors = true;
		}

		if (hasErrors) {
			return false;
		}

		return true;
	}
}
