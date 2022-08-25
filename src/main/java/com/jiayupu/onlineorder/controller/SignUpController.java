package com.jiayupu.onlineorder.controller;

import com.jiayupu.onlineorder.entity.Customer;
import com.jiayupu.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

// 必须加上，否则request mapping无法被dispatch servlet detect到
@Controller
// class名字要跟业务逻辑有关联
public class SignUpController {

   private CustomerService customerService;
   @Autowired
   public SignUpController(CustomerService customerService) {
      this.customerService = customerService;
   }

   // 标注这个方法是处理signup的请求的
   @RequestMapping(value = "/signup", method = RequestMethod.POST)
   // 标注请求的状态，类似于servlet里面doGet的response.setStatus()
   // 这里没有返回的type，是void，所以必须要用@ResponseStatus来定义status
   /**
    * 如果不是void：
    * public Object signUp(@RequestBody Customer customer, HttpServletResponse response) {
    *    response.setStatus();
    * }
    */
   @ResponseStatus(value = HttpStatus.CREATED)
   // @ResponseBody JAVA -> JSON
   public void signUp(@RequestBody Customer customer) {
      // 不用解析JSON，自动完成，JSON -> JAVA
      System.out.println("customer name " + customer.getFirstName());
      customerService.signUp(customer);
   }

}
