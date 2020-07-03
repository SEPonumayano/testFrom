package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {

	@Autowired
	 UserService userService;

	//一覧ページ
	 @GetMapping(value = "/user/list")
	  public String displayList(Model model) {
	    List<User> userlist = userService.searchAll();
	    model.addAttribute("userlist", userlist);
	    return "user/list";
	  }


	 //syousai
	 @GetMapping("/user/{id}")
	 public String displayView(@PathVariable Long id,Model model) {
		 User user =userService.findById(id);
		 model.addAttribute("userRequest",user);
		 return "user/view";
	 }

	 //編集
	 @GetMapping("/user/{id}/edit")
	    public String displayEdit(@PathVariable Long id, Model model) {
	        User user = userService.findById(id);
	        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
	        userUpdateRequest.setId(user.getId());
	        userUpdateRequest.setName(user.getName());
	        //userUpdateRequest.setPhone(user.getPhone());
	        userUpdateRequest.setAddress(user.getAddress());
	        model.addAttribute("userUpdateRequest", userUpdateRequest);
	        return "user/edit";
	    }

	 @RequestMapping(value="/user/update", method=RequestMethod.POST)
	    public String update(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest, BindingResult result, Model model) {
	        if (result.hasErrors()) {
	            List<String> errorList = new ArrayList<String>();
	            for(ObjectError error : result.getAllErrors()) {
	                errorList.add(error.getDefaultMessage());
	            }
	              model.addAttribute("validationError", errorList);
	              return "user/edit";
	            }
	        // ユーザー情報の更新
	        userService.update(userUpdateRequest);
	        return String.format("redirect:/user/list");
	    }

	 //登録ページ
	 @GetMapping(value = "/user/add")
	  public String displayAdd(Model model) {
	    model.addAttribute("userRequest", new UserRequest());
	    return "user/add";
	  }

	 //エラー出力
	 @RequestMapping(value = "/user/create", method = RequestMethod.POST)
	  public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	      List<String> errorList = new ArrayList<String>();
	      for (ObjectError error : result.getAllErrors()) {
	        errorList.add(error.getDefaultMessage());
	      }

	      model.addAttribute("validationError", errorList);
	      return "user/add";
	    }

	    userService.create(userRequest);
	    return "redirect:/user/list";
	  }


}
