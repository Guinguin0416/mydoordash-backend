package com.jiayupu.onlineorder;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import com.jiayupu.onlineorder.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet") // @WebServlet让Tomcat知道有这个Servel的存在
// value = "..."告知Tomcat我们的map关系
// 例子：网址：http://www.laioffer.com/hello-servlet
/**
 * Servlet是JAVA中特殊的class，接受来自前端的request，按照请求参数的不同处理请求，再以response的形式返回给前端
 * e.g 比如search功能需要有一个searchServlet
 * 后端一个servlet对应一个功能，比如search，login，checkout，register
 * 前端的请求，打到不同的URL，后端根据URL的endpoint来对应到不同功能的servlet
 * 所有的请求，先发送到Tomcat，Tomcat根据请求的参数不同，把请求对应到不同的Servlet
 * Tomcat完成请求分发 ——> 需要知道：
 * 1）有哪些Servlet，每个Servlet叫什么
 * 2）Map关系是什么，URL对应哪个Servlet class
 * 各个Servlet的Response怎么返回给前端？所有的response都返回给Tomcat，然后由Tomcat把真正的response返回给Client（我们不关心怎么具体返回的）
 */
public class HelloServlet extends HttpServlet {
   private String message;

   public void init() {
      message = "Hello World!";
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // response.setContentType("text/html"); // data format返回的数据类型，其他的比如json，text/plain, 不写也可以
      response.setContentType("application/json");
      // request.getMethod() == "GET"
      // request.getProtocol() == "HTTP"
      // request抽象成object，有各种参数
      // response返回的东西


      // Hello
      // 返回的具体内容
      // String customer = request.getParameter("username"); // username=vincent -> 和URL末端一致
      PrintWriter out = response.getWriter(); // handler object that can print data into response only
      // out.println("<html><body>");
      // out.println("<h1>Hello " + customer + "</h1>");
      // out.println("</body></html>"); // 打印到response body
      // System.out.println 是打印到标准化输出窗口，stdout，比如IDE用就是console，terminal里就是命令行

       JSONObject object = new JSONObject();
       object.put("email", "sun@laioffer.com");
       object.put("name", "Rick Sun");
       object.put("age", 65);
       out.println(object);


//      response.setContentType("application/json");
//      ObjectMapper mapper = new ObjectMapper();
//      Customer customer= new Customer();
//      customer.setEmail("sun@laioffer.com");
//      customer.setPassword("123456");
//      customer.setFirstName("rick");
//      customer.setLastName("sun");
//      customer.setEnabled(true);
//
//      response.getWriter().print(mapper.writeValueAsString(customer));

   }
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      // Read customer information from request body
      JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
      String email = jsonRequest.getString("email");
      String firstName = jsonRequest.getString("first_name");
      String lastName = jsonRequest.getString("last_name");
      int age = jsonRequest.getInt("age");
      // Print customer information to IDE console
      System.out.println("Email is: " + email);
      System.out.println("First name is: " + firstName);
      System.out.println("Last name is: " + lastName);
      System.out.println("Age is: " + age);
      // Return status = ok as response body to the client
      response.setContentType("application/json");
      JSONObject jsonResponse = new JSONObject();
      jsonResponse.put("status", "ok");
      response.getWriter().print(jsonResponse);
   }

   public void destroy() {
   }
}
/**
 * 为什么需要doGet？
 * 因为发送http请求的时候，还需要其他东西
 * http method: GET/POST/PUT/DELETE
 * 不同method对应不同的function，e.g: doGet, doPost...（名字固定，必须叫doXXX，Tomcat决定的）
 * Tomcat逻辑步骤：
 * 1. Receive request
 * 2. Parse URL -> which Servlet to use
 * 3. Parse HTTP Method -> which method to call
 * 4. HelloServlet.doGet()/ doPost()/...
 */

/**
 * localhost：发给自己
 * 8080：HTTP port，Tomcat实时监控这个端口的情况
 * 电脑上有很多程序，port决定请求交给特定的程序接收，这里是交给Tomcat
 * 端口数字的上限：65535（0 - 65535），2^16，操作系统最多能监控这么多程序
 * localhost:8080 默认使用index.jsp返回，默认主页；否则，用对应Servlet e.g localhost:8080/hello-servlet
 * 也可以用别的默认主页，但必须叫index.html，优先级：index.html > index.jsp
 *
 * Maven：build可以单独运行的压缩文件（所有src的code）
 * .war：Java + JS + HTML（Java + 所有web配置文件）
 * .jar：
 */


