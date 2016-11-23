package bg.softuni.web.beans.admin;

import bg.softuni.entity.UserModel;
import bg.softuni.entity.enums.UserTypes;
import bg.softuni.service.UserServiceLocal;
import bg.softuni.web.utils.GeneralUtils;
import bg.softuni.web.utils.MessageUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.regex.Pattern;

@ManagedBean(name = "createUserBean")
@RequestScoped
public class CreateUserBean {

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

        userService.save(user);
        MessageUtils.addMessage("user.successfullyCreated");


        FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);

        return "/blog/admin/admin-panel?faces-redirect=true";
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
