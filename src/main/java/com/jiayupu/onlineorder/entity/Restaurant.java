package com.jiayupu.onlineorder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant implements Serializable {

   private static final long serialVersionUID = 2455760938054036111L;

   @Id
   private int id;

   private String address;

   private String name;

   private String phone;

   private String imageUrl;
   // one to many的创建可以有两个方式实现：FK 和 Join table（Hibernate默认的实现方式）
   // 常用的方式：变成双向的，然后加一个FK，就不需要额外维护一个表
   // mappedBy：表示FK定义在MenuItem里
   // fetch：EAGER - 拿到R的时候，所有信息已经准备好；LAZY - 需要用的时候再读取信息
   @OneToMany(mappedBy = "restaurant",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<MenuItem> menuItemList; // List，Set，Map都可以，只要是collection


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public List<MenuItem> getMenuItemList() {
      return menuItemList;
   }

   public void setMenuItemList(List<MenuItem> menuItemList) {
      this.menuItemList = menuItemList;
   }
}

