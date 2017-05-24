package com.xiaobitipao.sample.spring4.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.xiaobitipao.sample.spring4.conf.PropertiesConf;
import com.xiaobitipao.sample.spring4.exception.DataNotFoundException;
import com.xiaobitipao.sample.spring4.model.User;
import com.xiaobitipao.sample.spring4.service.UserService;
import com.xiaobitipao.sample.spring4.web.form.UserForm;

@Controller
@RequestMapping({ "/user" })
public class UserController {

    // @InitBinder
    // public void initBinder(WebDataBinder webDataBinder) {
    // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    // webDataBinder.registerCustomEditor(Date.class, new
    // CustomDateEditor(dateFormat, true));
    // }

    @Autowired
    private UserService userService;

    @Autowired
    private PropertiesConf propertiesConf;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute(new UserForm());
        return "user/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegister(@Valid UserForm userForm, Errors errors) throws IllegalStateException, IOException {

        // 发生Validate Error的时候返回自画面
        if (errors.hasErrors()) {
            return "user/register";
        }

        // 处理文件上传
        MultipartFile profilePicture = userForm.getProfilePicture();
        if (!profilePicture.isEmpty()) {
            String destPath = propertiesConf.MULTIPART_LOCATION_REAL + profilePicture.getOriginalFilename();
            profilePicture.transferTo(new File(destPath));
        } else {
            throw new MaxUploadSizeExceededException(propertiesConf.MULTIPART_MAX_FILE_SIZE);
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setAge(userForm.getAge());
        user.setBirthday(userForm.getBirthday());

        long userid = userService.saveUser(user);

        return "redirect:/user/" + userid;
    }

    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("userid") String userid, Model model) {

        User user = userService.findUser(userid);
        if (null == user) {
            throw new DataNotFoundException("指定条件的数据不存在！", "/");
        }

        model.addAttribute(user);

        return "user/profile";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(Model model) {

        List<User> list = userService.findUsers();
        if (null == list) {
            throw new DataNotFoundException("指定条件的数据不存在！", "/");
        }

        model.addAttribute(list);

        return "user/list";
    }
}
