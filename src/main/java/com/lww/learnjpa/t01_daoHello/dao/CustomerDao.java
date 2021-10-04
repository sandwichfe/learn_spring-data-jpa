package com.lww.learnjpa.t01_daoHello.dao;

import com.lww.learnjpa.t01_daoHello.entiy.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 继承 了  spring data jpa 的 dao
 * JpaRepository 让它 拥有了基本的 crud 操作
 * JpaSpecificationExecutor  能够有 复杂查询 以及分页的 能力
 *
 * @author lww
 * @version 1.0
 * @description: to test
 * @date 2021/10/4 22:28
 */
public interface CustomerDao extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {


}
