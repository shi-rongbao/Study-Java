package com.study.javabeen;

public class User {
    private String user;
    private String password;
    private String checkPassword;
    private String email;
    private String gender;
    private int age;


    public User() {
    }

    public User(String user, String password, String checkPassword, String email, String gender, int age) {
        this.user = user;
        this.password = password;
        this.checkPassword = checkPassword;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    /**
     * 获取
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return checkPassword
     */
    public String getCheckPassword() {
        return checkPassword;
    }

    /**
     * 设置
     * @param checkPassword
     */
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }
}
