package com.jiayupu.onlineorder.service;

import com.jiayupu.onlineorder.dao.CustomerDao;
import com.jiayupu.onlineorder.entity.Cart;
import com.jiayupu.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// 请求具体要做的事情由service处理
public class CustomerService {
   private CustomerDao customerDao;

   @Autowired
   public CustomerService(CustomerDao customerDao) {
      this.customerDao = customerDao;
   }

   public void signUp(Customer customer) {
      Cart cart = new Cart();
      customer.setCart(cart); // 给customer分配一个购物车

      customer.setEnabled(true);
      customerDao.signUp(customer);

   }

   public Customer getCustomer(String email) {
      return customerDao.getCustomer(email);
   }


}
