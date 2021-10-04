package com.lww.learnjpa.t01_daoHello.entiy;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author lww
 */
@Data
@Entity
@Table(name = "customer")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Customer {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id",nullable = false)
    private String id;
    private String name;
    private String address;

}
