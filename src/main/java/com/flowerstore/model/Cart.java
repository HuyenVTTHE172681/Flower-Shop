/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.flowerstore.model;

/**
 *
 * @author DELL
 */
public class Cart {
     private int userID;
    private int productID;
    private int amount;

    public Cart() {
    }

    public Cart(int userID, int productID, int amount) {
        this.userID = userID;
        this.productID = productID;
        this.amount = amount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Cart [ " + "User ID = " + userID + ", Product ID = " + productID + ", amount = " + amount + ']' + "\n";
    }
}
