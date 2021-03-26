package com.project.daycare.api;

import com.project.daycare.module.Child;
import com.project.daycare.module.Parentstlflist;
import com.project.daycare.module.Payment;
import com.project.daycare.repository.ChildrenRepository;
import com.project.daycare.repository.PaymentRepository;
import com.project.daycare.repository.PhoneListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class api {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ChildrenRepository childrenRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    PhoneListRepository phoneListRepository;

    @GetMapping(value = "/children")
    public List<Child> getChildren(){
        return childrenRepository.findAll();
    }

    @PostMapping(value = "/children")
    public List<Child> insertChild(@RequestBody final Child child){
        String SQL = "INSERT INTO child (name,age,address,phone_number,email) VALUE ( '" +child.getName()+"',"+child.getAge()+",'"+child.getAddress()+"','"+child.getPhoneNumber()+"','"+child.getEmail()+"')";
        jdbcTemplate.execute(SQL);
        return childrenRepository.findAll();
    }

    @DeleteMapping(value="/children/{id}")
    public List<Child> deleteChild(@PathVariable(value="id") String id){
        String SQL = "DELETE FROM child WHERE id="+id;
        jdbcTemplate.execute(SQL);
        return childrenRepository.findAll();
    }

    @PutMapping(value="/children")
    public List<Child> updateChild(@RequestBody final Child child){
        String SQL = "UPDATE child SET name = '" +child.getName()+"',age = "+child.getAge()+",address='"+child.getAddress()+"',phone_number='"+child.getPhoneNumber()+"',email='"+child.getEmail()+"' WHERE id=" +child.getId();
//        System.out.println(SQL);
        jdbcTemplate.execute(SQL);
        return childrenRepository.findAll();
    }

    @GetMapping(value = "/payment")
    public List<Payment> getPayment(){
        return paymentRepository.findAll();
    }
    @PostMapping(value = "/payment")
    public List<Payment> insertPayment(@RequestBody final Payment payment){
        String SQL = "INSERT INTO payment (member_id,amount,paid,description,due) VALUE ( '" +payment.getMember_id()+"',"+payment.getAmount()+",'"+payment.getPaid()+"','"+payment.getDescription()+"','"+payment.getDue()+"')";
        jdbcTemplate.execute(SQL);
        return paymentRepository.findAll();
    }
    @DeleteMapping(value="/payment/{id}")
    public List<Payment> deletePayment(@PathVariable(value="id") String id){
        String SQL = "DELETE FROM payment WHERE id="+id;
        jdbcTemplate.execute(SQL);
        return paymentRepository.findAll();
    }
    @PutMapping(value="/payment")
    public List<Payment> updatePayment(@RequestBody final Payment payment){
        String SQL = "UPDATE payment SET member_id = '" +payment.getMember_id()+"',amount = "+payment.getAmount()+",due='"+payment.getDue()+"',paid='"+payment.getPaid()+"',description='"+payment.getDescription()+"' WHERE id=" +payment.getId();

        jdbcTemplate.execute(SQL);
        return paymentRepository.findAll();
    }

    @GetMapping(value = "/phonelist")
    public List<Parentstlflist> getPhoneList(){
        return phoneListRepository.findAll();
    }

    @PostMapping(value = "/phonelist")
    public List<Parentstlflist> insertPhoneList(@RequestBody final Parentstlflist phoneDetails){
        String SQL = "INSERT INTO parentstlflist (parent_first_name,parent_last_name,register_date,phone_number) VALUE ( '"+phoneDetails.getParentFirstName()+"','"+phoneDetails.getParentLastName()+"','"+phoneDetails.getRegisterDate()+"','"+phoneDetails.getPhoneNumber()+"')";
        jdbcTemplate.execute(SQL);
        return phoneListRepository.findAll();
    }

    @DeleteMapping(value="/phonelist/{id}")
    public List<Parentstlflist> deletePhoneList(@PathVariable(value="id") String id){
        String SQL = "DELETE FROM parentstlflist WHERE id="+id;
        jdbcTemplate.execute(SQL);
        return phoneListRepository.findAll();
    }

    @PutMapping(value="/phonelist")
    public List<Parentstlflist> updatePhoneList(@RequestBody final Parentstlflist phoneDetails){
        String SQL = "UPDATE parentstlflist SET phone_number = '" +phoneDetails.getPhoneNumber()+"',parent_first_name='"+phoneDetails.getParentFirstName()+"',parent_last_name='"+phoneDetails.getParentLastName()+"' WHERE id=" +phoneDetails.getId();
        System.out.println(SQL);
        jdbcTemplate.execute(SQL);
        return phoneListRepository.findAll();
    }
}
