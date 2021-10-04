package com.lww.learnjpa.t01_daoHello.controller;

import com.lww.learnjpa.t01_daoHello.dao.CustomerDao;
import com.lww.learnjpa.t01_daoHello.entiy.Customer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Optional;

/**
 * @author lww
 * @version 1.0
 * @description: to test
 * @date 2021/10/4 22:28
 */
@RestController
@RequestMapping("/customer")
public class UserController {

    @Resource
    private CustomerDao customerDao;

    @RequestMapping("/test")
    public Customer test() {
        Customer customer = new Customer();
        customer.setName("aa");
        customer.setAddress("bgf");
        return customerDao.save(customer);
    }


    /**
     * 根据条件查询单个对象
     */
    @GetMapping("/testSpec")
    public Customer testSpec() {
        // 自定义查询条件
        Specification<Customer> specification = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                //1. 实体的属性
                Path<String> addressPath = root.get("address");
                //2. 查询条件
                Predicate predicate = criteriaBuilder.equal(addressPath, "bgf");
                return predicate;
            }
        };
        Optional<Customer> one = customerDao.findOne(specification);
        Customer customer = one.isPresent() ? one.get() : null;
        return customer;
    }

    /**
     * 根据多个条件查询单个对象
     */
    @GetMapping("/testSpecOneAnd")
    public Customer testSpecOneAnd() {
        // 自定义查询条件
        Specification<Customer> specification = (root, query, criteriaBuilder) -> {
            //1. 实体的属性
            Path<String> addressPath = root.get("address");
            Path<String> namePath = root.get("name");
            //2. 查询条件
            Predicate p1 = criteriaBuilder.equal(addressPath, "bgf");
            Predicate p2 = criteriaBuilder.equal(namePath, "lww");
            //3. 将多个查询条件组合到一起   and  或  or   该如何来实现
            /**
             *    and          criteriaBuilder.and(p1,p2);    条件 连接
             *    or           criteriaBuilder.or(p1,p2);     条件 or
             */
            Predicate predicateAnd = criteriaBuilder.and(p1, p2);
            return predicateAnd;
        };
        Optional<Customer> one = customerDao.findOne(specification);
        Customer customer = one.isPresent() ? one.get() : null;
        return customer;
    }

}
