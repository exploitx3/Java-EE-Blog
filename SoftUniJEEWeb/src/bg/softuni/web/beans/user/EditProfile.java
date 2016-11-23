package bg.softuni.web.beans.user;

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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@ManagedBean(name = "editProfile")
@ViewScoped
public class EditProfile {

    @Inject
    HttpServletRequest request;

    @EJB
    UserServiceLocal userService;

    private UserModel user;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    @PostConstruct
    public void init() {
        user = (UserModel) request.getSession().getAttribute("LOGGED_USER");
    }

    public String updateAction() {

        if (!validate()) {
            return null;
        }

        String cryptedPassword = GeneralUtils.encodeSha256Password(user.getPassword());
        user.setPassword(cryptedPassword);

        userService.update(user);

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
            MessageUtils.addErrorMessage("username", "error.required.username");
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

    /**
     * Verifies if a error messages are present in the context
     */
    public boolean hasErrors() {
        Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
        for (; messages.hasNext(); ) {
            FacesMessage message = messages.next();
            if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
                return true;
            }
        }

        return false;
    }
}
