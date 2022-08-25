package com.jiayupu.onlineorder.service;

import com.jiayupu.onlineorder.dao.MenuInfoDao;
import com.jiayupu.onlineorder.entity.MenuItem;
import com.jiayupu.onlineorder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoService {

   @Autowired
   private MenuInfoDao menuInfoDao;


   public List<Restaurant> getRestaurants() {
      return menuInfoDao.getRestaurants();
   }

   public List<MenuItem> getAllMenuItem(int restaurantId) {
      return menuInfoDao.getAllMenuItem(restaurantId);
   }

   public MenuItem getMenuItem(int id) {
      return menuInfoDao.getMenuItem(id);
   }
}

