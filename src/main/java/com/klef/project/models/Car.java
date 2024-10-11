package com.klef.project.models;


import java.io.Serializable;
import java.util.Base64;
import javax.persistence.*;

@Entity
@Table(name="car_table")
public class Car implements Serializable {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="car_id")
    private int id;

    @Column(name="car_name",nullable = false)
    private String name;

    @Column(name="car_model",nullable = false)
    private String model;

    @Column(name="car_description", nullable = false)
    private String description;

    @Column(name="car_price",nullable = false)
    private double price;

    @Column(name="car_year",nullable = false)
    private int year;

    @Lob
    @Column(name ="car_image",nullable = false)
    private byte[] image;

    // Getters and Setters

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBase64Image() {
        if (image != null) {
            return Base64.getEncoder().encodeToString(image);
        }
        return null;
    }
}