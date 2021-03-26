package com.project.daycare.module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class PhoneList {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;



    private String lastName;
    private String phoneNumber;
    private String  registerDate;

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public PhoneList() {
    }
    public String getRegisterDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        registerDate = formatter.format(date);
        return registerDate;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return firstName + lastName;
    }

    public void setName(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    @Override
    public String toString() {
        return "PhoneNumbers{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}