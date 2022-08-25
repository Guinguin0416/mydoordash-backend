package com.jiayupu.onlineorder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable  {

   private static final long serialVersionUID = 2652327633296064143L;

   @Id
   private String email;

   private String firstName;

   private String lastName;

   private String password;

   private boolean enabled;

   // CascadeType：定义所有操作都要做，集联操作的权限
   // JoinColumn,必须要有，否则读取的时候有问题
   @OneToOne(cascade = CascadeType.ALL) // 相当于建立FK
   @JoinColumn(unique = true) // 不同的customer指向不同的cart ID（foreign key）
   // OneToOne & JoinColumn同时使用，保证1v1关系
   private Cart cart;
   // customer里有一个foreign key指向cart


   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
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

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

}
