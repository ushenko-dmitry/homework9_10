package ru.mail.dimaushenko.service.model;

public class FullUserDTO {

    private Integer id;
    private String username;
    private String password;
    private boolean isActive;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "FullUserDTO{" + "id=" + id + ", username=" + username + ", password=" + password + ", isActive=" + isActive + ", age=" + age + '}';
    }

}
