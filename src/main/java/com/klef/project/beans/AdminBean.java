package com.klef.project.beans;


import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.project.models.Admin;
import com.klef.project.services.AdminService;



@ManagedBean(name="adminbean",eager = true)
public class AdminBean 
{
	@EJB(lookup="java:global/EPProject/AdminServiceImpl!com.klef.project.services.AdminService")
  AdminService adminService;
  private String adusername;
  private String adpassword;
  public String getUsername() {
	  return adusername;
	}
	@Override
	public String toString() {
	  return "AdminBean [username=" + adusername + ", password=" + adpassword + "]";
	}
	public void setUsername(String username) {
	  this.adusername = username;
	}
	public String getPassword() {
	  return adpassword;
	}
	public void setPassword(String password) {
	  this.adpassword = password;
	}

	  public void validateadminlogin() throws IOException
	  {
	   FacesContext facesContext = FacesContext.getCurrentInstance();
	     ExternalContext externalContext = facesContext.getExternalContext();
	  
	     HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
	   HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
	  
	   Admin admin = adminService.checkadminlogin(adusername, adpassword);
	   
	   if(admin!=null)
	   {
	     HttpSession session = request.getSession();
	     session.setAttribute("admin", admin);
	    
	     response.sendRedirect("adminhome.jsp");
	   }
	   else
	   {
	     response.sendRedirect("adminloginfail.jsf");
	   }
	  }
	}