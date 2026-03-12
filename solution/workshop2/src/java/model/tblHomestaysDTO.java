/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author khoin
 */
public class tblHomestaysDTO {
    private String homeId;
    private String description; 
    private int price;
    private String address;
    private String userId;

    public tblHomestaysDTO() {
    }

    public tblHomestaysDTO(String homeId, String description, int price, String address, String userId) {
        this.homeId = homeId;
        this.description = description;
        this.price = price;
        this.address = address;
        this.userId = userId;
    }

    /**
     * @return the homeId
     */
    public String getHomeId() {
        return homeId;
    }

    /**
     * @param homeId the homeId to set
     */
    public void setHomeId(String homeId) {
        this.homeId = homeId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
