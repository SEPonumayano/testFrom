package com.example.demo.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

	 @Autowired
	  private UserRepository userRepository;

	 public List<User> searchAll() {
		    return userRepository.findAll();
		  }

	 public void create(UserRequest userRequest) {
		    Date now = new Date();
		    User user = new User();
		    user.setName(userRequest.getName());
		    user.setAddress(userRequest.getAddress());
		    userRepository.save(user);
		  }
}
