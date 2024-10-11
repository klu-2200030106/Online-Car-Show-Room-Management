package com.klef.project.services;



import java.util.List;

import javax.ejb.Remote;

import com.klef.project.models.Admin;
import com.klef.project.models.Seller;

@Remote
public interface AdminService 
{
  public Admin checkadminlogin(String username,String password);

}
