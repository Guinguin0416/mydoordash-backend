package com.jiayupu.onlineorder.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

   private static final long serialVersionUID = 8436097833452420298L; // 每个class独立，可以重复

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) // 系统自动generate一个数
   // 因为这里的id本身没有意义，是自增主键
   private int id;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
   private List<OrderItem> orderItemList;


   private double totalPrice;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public List<OrderItem> getOrderItemList() {
      return orderItemList;
   }

   public void setOrderItemList(List<OrderItem> orderItemList) {
      this.orderItemList = orderItemList;
   }

   public double getTotalPrice() {
      return totalPrice;
   }

   public void setTotalPrice(double totalPrice) {
      this.totalPrice = totalPrice;
   }
}

