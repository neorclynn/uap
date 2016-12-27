package uap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uap.bo.UserBo;

@Controller
@RequestMapping("/view/user")
public class UserController {
    private UserBo userBo;

    @Autowired
    @Required
    public void setUserBo(UserBo userBo) {
        this.userBo = userBo;
    }

    @RequestMapping
    public ModelAndView home() {
        ModelAndView result = new ModelAndView("user");
        result.addObject("users", userBo.findAll());
        return result;
    }
}
