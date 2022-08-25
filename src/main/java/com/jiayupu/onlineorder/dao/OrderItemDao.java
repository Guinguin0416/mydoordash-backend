package com.jiayupu.onlineorder.dao;

import com.jiayupu.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDao {

   @Autowired
   private SessionFactory sessionFactory;

   public void save(OrderItem orderItem) {
      Session session = null;
      try {
         session = sessionFactory.openSession();
         session.beginTransaction();
         session.save(orderItem);
         session.getTransaction().commit();

      } catch (Exception ex) {
         ex.printStackTrace();
         if (session != null) session.getTransaction().rollback();
         // 这里不可以写成try with resoure，否则这里没法用rollback，session拿不到
      } finally {
         if (session != null) {
            session.close();
         }
      }
   }
}


