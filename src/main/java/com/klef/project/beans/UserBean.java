package com.klef.project.beans;

import javax.ejb.EJB;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.project.models.User;
import com.klef.project.services.UserService;

@ManagedBean(name="userbean",eager=true)
public class UserBean 
{
	@EJB(lookup="java:global/EPProject/UserServiceImpl!com.klef.project.services.UserService")
	UserService userservice;
	private String name;
	private String gender;
	private String email;
	private String password;
	private String contact;
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String add()
	{
		User u=new User();
		u.setName(name);
		u.setGender(gender);
		u.setEmail(email);
		u.setPassword(password);
		u.setContact(contact);
		
		userservice.adduser(u);
		

		return "userlogin.jsf";
		
	}
	public void validatelogin() throws IOException
	{
		FacesContext facesContext=FacesContext.getCurrentInstance();
		ExternalContext externalContext=facesContext.getExternalContext();
		HttpServletRequest request=(HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response=(HttpServletResponse) externalContext.getResponse();
		
		User u=userservice.checkuserlogin(email, password);
		if(u!=null)
		{
			
			HttpSession session=request.getSession();
			session.setAttribute("user", u);   
			
			response.sendRedirect("userhome.jsp");
		}
		else
		{
			
			
			response.sendRedirect("userloginfail.jsf");
		}
}
	private List<User> sellerlist;

    public List<User> getSellerlist() {
        if (sellerlist == null) {
            sellerlist = userservice.viewallusers();  // Initialize sellerlist if it's null
        }
        return sellerlist;
    }
    
    
}
