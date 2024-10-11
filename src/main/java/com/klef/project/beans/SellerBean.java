package com.klef.project.beans;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.project.models.Seller;
import com.klef.project.services.SellerService;

@ManagedBean(name="sellerbean",eager = true)
public class SellerBean 
{
	@EJB(lookup="java:global/EPProject/SellerServiceImpl!com.klef.project.services.SellerService")
	SellerService sellerservice;
	private int id;
	private String name;
	private String gender;
	private String email;
	private String password;
	private String contact;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		Seller e = new Seller();
        e.setId(id);
		e.setName(name);
		e.setGender(gender);
		e.setContact(contact);
		e.setEmail(email);
		e.setPassword(password);
		
		sellerservice.addseller(e);
		
		return "selleradd.jsf";
		
	}
	public void validatelogin() throws IOException
	{
		FacesContext facesContext=FacesContext.getCurrentInstance();
		ExternalContext externalContext=facesContext.getExternalContext();
		HttpServletRequest request=(HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response=(HttpServletResponse) externalContext.getResponse();
		
		Seller seller=sellerservice.checksellerlogin(email, password);
		if(seller!=null)
		{
			
			HttpSession session=request.getSession();
			session.setAttribute("seller", seller);   
			
			response.sendRedirect("sellerhome.jsp");
		}
		else
		{
			response.sendRedirect("sellerloginfail.jsf");
		}
	}
	private List<Seller> sellerlist;
	public List<Seller> getSellerlist() {
		 if (sellerlist == null) {
		sellerlist=sellerservice.viewallsellers();
		 }
		 return sellerlist;
  }
	public String delete(int eid)
	{
		sellerservice.deleteseller(eid);
		return "deletesucess.jsf";
	}
	public String update()
	  {
	    Seller s=sellerservice.viewsellerbyid(id);
	    if(s!=null)
	    {
	      Seller e = new Seller();
	      e.setId(id);
	      e.setName(name);
	      e.setPassword(password);
	      e.setContact(contact);
	      
	      sellerservice.updateseller(e);
	      return "sellerupdatesuccess.jsf";

	    }
	    else
	    {
	      System.out.println("Id not Found");
	      return "sellerupdatefail.jsf";
	    }
	  }
}
