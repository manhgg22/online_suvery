/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Norttie
 */
public class User {
    private int user_id;
    private String username;
    private String password;
    private String displayname;
    private String description;
    private String address;
    private String avatar;
    private String role;

    public User() {
    }

    public User(int user_id, String username, String password, String displayname, String description, String address, String avatar, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.description = description;
        this.address = address;
        this.avatar = avatar;
        this.role = role;
    }

    public User(String username, String password, String displayname, String description, String address, String avatar, String role) {
        this.username = username;
        this.password = password;
        this.displayname = displayname;
        this.description = description;
        this.address = address;
        this.avatar = avatar;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "user_id=" + user_id + ", username=" + username + ", password=" + password + ", displayname=" + displayname + ", description=" + description + ", address=" + address + ", avatar=" + avatar + ", role=" + role + '}';
    }
    
    
    
}
