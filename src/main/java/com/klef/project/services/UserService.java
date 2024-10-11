package com.klef.project.services;

import java.util.List;

import javax.ejb.Remote;


import com.klef.project.models.User;
@Remote
public interface UserService 
{
	 public String adduser(User user);
	 public User checkuserlogin(String email,String password);
	 public List<User> viewallusers();
}
