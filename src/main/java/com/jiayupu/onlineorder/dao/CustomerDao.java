package com.jiayupu.onlineorder.dao;

import com.jiayupu.onlineorder.entity.Authorities;
import com.jiayupu.onlineorder.entity.Customer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// @Repository: spring的一个annotation，自带@Component，与数据库交互
@Repository
public class CustomerDao {
   @Autowired
   private SessionFactory sessionFactory;

   public void signUp(Customer customer) {
      Authorities authorities = new Authorities();
      authorities.setAuthorities("ROLE_USER");
      // 创建用户相关的权限，string可以任意定义，只要和security框架里的api对应即可
      authorities.setEmail(customer.getEmail());

      Session session = null;
      try {
         session = sessionFactory.openSession(); // 拿到对数据库操作的session
         session.beginTransaction(); // 表示下面几行就是要对DB的操作
         session.save(authorities);
         session.save(customer);
         session.getTransaction().commit(); // commit()真正意义上开始操作

      } catch (Exception ex) {
         ex.printStackTrace();
         if (session != null) session.getTransaction().rollback();
      } finally {
         if (session != null) {
            session.close();
         }
      }

   }

   public Customer getCustomer(String email) {
      Customer customer = null;
      Session session = null;
      try {
         session = sessionFactory.openSession();
         customer = session.get(Customer.class, email);
         // get代表搜索Customer.class -> customer table
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         if (session != null) session.close();
      }
      return customer;
   }

}



