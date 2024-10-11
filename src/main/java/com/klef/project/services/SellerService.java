package com.klef.project.services;

import java.util.List;

import com.klef.project.models.Seller;

public interface SellerService 
{
    public String addseller(Seller seller);
    public Seller checksellerlogin(String email,String password);
    public List<Seller> viewallsellers();
    public String deleteseller(int sid);
    public String updateseller(Seller seller);
    public Seller viewsellerbyid(int sid);
}