

package com.project.daycare.module;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Parentstlflist {
@Id
@GeneratedValue
private Integer id;
private String parentFirstName;


private String parentLastName;
private String phoneNumber;
private String registerDate;

public String getParentFirstName(){
return parentFirstName;
}

public void setParentFirstName(String parentFirstName) {
this.parentFirstName = parentFirstName;
}

public String getParentLastName() {
return parentLastName;
}

public void setParentLastName(String parentLastName) {
this.parentLastName = parentLastName;
}
public Parentstlflist() {
}
public String getRegisterDate(){

return registerDate;
}
public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}
public void setDate(String registerDate){
this.registerDate = registerDate;

}
public void setName(String firstName,String lastName) {
this.parentFirstName = firstName;
this.parentLastName = lastName;
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
",parent_first_name='" + parentFirstName + '\'' +
",parent_last_name='" + parentLastName + '\'' +
",register_date='" + registerDate +'\''+
",phone_number='" + phoneNumber + '\'' +

'}';
}
}

