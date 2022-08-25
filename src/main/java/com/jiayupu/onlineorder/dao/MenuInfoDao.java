package com.jiayupu.onlineorder.dao;

import com.jiayupu.onlineorder.entity.MenuItem;
import com.jiayupu.onlineorder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

   @Autowired
   private SessionFactory sessionFactory;

   public List<Restaurant> getRestaurants() {
      try (Session session = sessionFactory.openSession()) {
         // 主要围绕session展开，基础功能CRUD，高级的方法用query
         // session.get是hibernate的方法
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
         // CriteriaQuery可用于查找，filter等功能
         // 需要获得一个类似于集合的东西，必须用query
         // session不自带返回集合的功能，只有基础的增删改查
         criteria.from(Restaurant.class); // from 相当于select*from
         return session.createQuery(criteria).getResultList();
      } catch (Exception ex) {
         ex.printStackTrace();
      }

      return new ArrayList<>();
   }



   public List<MenuItem> getAllMenuItem(int restaurantId) {
      try (Session session = sessionFactory.openSession()) {
         Restaurant restaurant = session.get(Restaurant.class, restaurantId);
         if (restaurant != null) {
            return restaurant.getMenuItemList();
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return new ArrayList<>();
   }

   public MenuItem getMenuItem(int menuItemId) {
      try (Session session = sessionFactory.openSession()) {
         return session.get(MenuItem.class, menuItemId);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }
}

