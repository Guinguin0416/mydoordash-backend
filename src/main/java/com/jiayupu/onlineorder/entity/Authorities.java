package com.jiayupu.onlineorder.entity;

import java.io.Serializable;
// Serializable: 序列化，转换成字节流，存到硬盘上（因为本身object是存在内存上的）

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "authorities")
public class Authorities implements Serializable {

   private static final long serialVersionUID = 8734140534986494039L;
   // 存到数据库里的数据都有一个版本号，这个table的版本号，做了某些修改需要改这个数字
   // 用于检测业务逻辑是否改变，检测是否兼容
   // 每次多加一个column，是兼容的

   @Id
   private String email;

   private String authorities;

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getAuthorities() {
      return authorities;
   }

   public void setAuthorities(String authorities) {
      this.authorities = authorities;
   }
}


