package com.klef.project.beans;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.klef.project.models.Car;
import com.klef.project.services.CarService;

@ManagedBean(name = "carBean", eager = true)
public class CarBean {

    @EJB(lookup = "java:global/EPProject/CarServiceImpl!com.klef.project.services.CarService")
     CarService service;

    private int id;
    private String name;
    private String model;
    private String description;
    private double price;
    private int year;
    private Part carImage;
    private Car selectedCar;


    // Getters and setters for other fields
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Part getCarImage() {
        return carImage;
    }

    public void setCarImage(Part carImage) {
        this.carImage = carImage;
    }

    public List<Car> getCarList() {
        return service.viewallCars();
    }
    
    public String add() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Car car = new Car();
            car.setName(name);
            car.setModel(model);
            car.setDescription(description);
            car.setPrice(price);
            car.setYear(year);
            car.setImage(convertImageToByteArray(carImage));
            service.addCar(car);
            
            // Add success message
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Car added successfully"));

            // Return navigation outcome to go to 'caradd.jsf' page with redirect
            return "caradd?faces-redirect=true"; 
        } catch (Exception e) {
            // Add error message
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while adding the car. Please try again."));
            e.printStackTrace();
            
            // Return to the same page with error message
            return "caradd?faces-redirect=true"; 
        }
    }


    private byte[] convertImageToByteArray(Part carImage) {
        try (InputStream input = carImage.getInputStream()) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get Base64 image representation
    public String getImageBase64(Car car) {
        byte[] image = car.getImage();
        return image != null ? "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image) : null;
    }
    public String delete(Integer id) {
        service.deleteCar(id);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Car deleted successfully"));
        return "deletecarsucess.jsf";
    }
    public void downloadImage(int carId) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            Car car = service.viewCarById(carId); // Assuming a method to get car by ID
            byte[] image = car.getImage();
            if (image != null) {
                response.reset();
                response.setContentType("image/jpeg"); // Change MIME type if needed
                response.setHeader("Content-Disposition", "attachment; filename=\"car_image.jpg\"");
                OutputStream output = response.getOutputStream();
                output.write(image);
                output.flush();
                output.close();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Image not found.");
                context.addMessage(null, message);
            }
        } catch (IOException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while downloading the image.");
            context.addMessage(null, message);
            e.printStackTrace();
        }
        context.responseComplete();
    }

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String idParam = params.get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            selectedCar = service.viewCarById(id); // Method to get car by ID
        }
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    
    public String update() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Car car = service.viewCarById(id);
            if (car != null) {
                car.setName(name);
                car.setModel(model);
                car.setDescription(description);
                car.setPrice(price);
                service.updateCar(car);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Car updated successfully"));
                return "carupdatesuccess"; 
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Car not found."));
                return "carupdatefail"; 
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while updating the car."));
            e.printStackTrace();
            return "updatefail"; 
        }
    }
    
}