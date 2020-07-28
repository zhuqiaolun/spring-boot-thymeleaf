package com.demon.springbootthymeleaf.controller;

import com.demon.springbootthymeleaf.support.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @ClassName: MainController
 * @Description:
 * @Author: Demon
 * @Date: 2020/7/19 19:11
 */
@Controller
public class MainController extends BaseController {

    @ModelAttribute
    public void populateModel(Model model) {
        model.addAttribute("roleMenus", this.getRoleMenus());
    }

    @GetMapping(value = "main")
    public String getMain() {
        return "main";
    }

    @GetMapping(value = "user")
    public String getUser() {
        return "user/user";
    }


    @GetMapping(value = "role")
    public String getRole() {
        return "role/role";
    }
}
