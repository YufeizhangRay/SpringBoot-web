package cn.zyf.springboot.springbootweb.controller;

import cn.zyf.springboot.springbootweb.model.Address;
import cn.zyf.springboot.springbootweb.model.User;
import cn.zyf.springboot.springbootweb.service.AddressService;
import cn.zyf.springboot.springbootweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.ManyToOne;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        boolean flag = session.getAttribute("loginUser")==null?true:false;
        if(flag){
            return "login";
        }else {
            return "redirect:/main";
        }
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        //接收我们的页面的用户名和密码的控制层
        //调用服务吃的login方法，判断是否登录成功
        //成功，就将User存入Session域空间中
        //不成功，给页面一个错误提示
        User u = userService.login(user);
        if (u != null) {
            //将u存如Session域空间
            session.setAttribute("loginUser",u);
            //跳转主页面
            return "redirect:/main";
        } else {
            model.addAttribute("loginErr","用户名或者密码错误！");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        //一个方法实现清理Session
        session.invalidate();
        return "redirect:/login";
    }


    @GetMapping("/userlist")
    public String userlist(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users",users);
        return "userlist";
    }

    @PostMapping("/userSearch")
    public String userSearch(String searchByUsername,Model model){
        List<User> users = userService.getByUsernameLike("%"+searchByUsername+"%");
        model.addAttribute("users",users);
        return "userlist";
    }

    //做数据有效性校验，参数必须有需要的类型
    @GetMapping("/addUser")
    public String addUserView(User user,Model model){
        //这里需要获得所有的地址信息
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses",addresses);
        return "addUser";
    }


    //@Valid User user, BindingResult errors必须挨着写
    @PostMapping("/addUser")
    public String addUser(@Valid User user, BindingResult errors,Model model){
        if (errors.getErrorCount() > 0) {
            List<Address> addresses = addressService.getAllAddresses();
            model.addAttribute("addresses",addresses);
            return "addUser";
        }
        user.setRegDate(new Date());
        userService.add(user);
        return "redirect:/userlist";
    }


    @GetMapping("/updateUser")
    public String updateUserView(Model model, Integer id){
        //注入被选中的对象
        User user = userService.get(id);
        model.addAttribute("user",user);
        //这里需要获得所有的地址信息
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses",addresses);
        return "updateUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(User user){
        userService.update(user);
        return "redirect:/userlist";
    }


    @GetMapping("/deleteUser")
    public String deleteUser(Integer id){
        userService.delete(id);
        return "redirect:/userlist";
    }

    @GetMapping("/testErr")
    public String testError(){
        int i = 10;
        int j = 0;
        double ans = i/j;
        return "userlist";
    }

    //优先级高于error文件夹，但仅仅在本类起作用
//    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
//    public ModelAndView doError(Exception e){
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("err",e);
//        mv.setViewName("myError");
//        return mv;
//    }


}
