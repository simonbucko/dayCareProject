package com.project.daycare.api;

import com.project.daycare.module.Children;
import com.project.daycare.module.Payment;
import com.project.daycare.repository.ChildrenRepository;
import com.project.daycare.repository.PaymentRepository;
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

    @GetMapping(value = "/children")
    public List<Children> getChildren(){
        return childrenRepository.findAll();
    }

    @PostMapping(value = "/children")
    public List<Children> insertChild(@RequestBody final Children child){
        String SQL = "INSERT INTO children (name,age,address,phone_number,email) VALUE ( '" +child.getName()+"',"+child.getAge()+",'"+child.getAddress()+"','"+child.getPhoneNumber()+"','"+child.getEmail()+"')";
        jdbcTemplate.execute(SQL);
        return childrenRepository.findAll();
    }

    @DeleteMapping(value="/children/{id}")
    public List<Children> deleteChild(@PathVariable(value="id") String id){
        String SQL = "DELETE FROM children WHERE id="+id;
        jdbcTemplate.execute(SQL);
        return childrenRepository.findAll();
    }

    @PutMapping(value="/children")
    public List<Children> updateChild(@RequestBody final Children child){
        String SQL = "UPDATE children SET name = '" +child.getName()+"',age = "+child.getAge()+",address='"+child.getAddress()+"',phone_number='"+child.getPhoneNumber()+"',email='"+child.getEmail()+"' WHERE id=" +child.getId();
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
}
