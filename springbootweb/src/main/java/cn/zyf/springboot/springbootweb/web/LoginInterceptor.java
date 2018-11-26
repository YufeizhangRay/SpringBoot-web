package cn.zyf.springboot.springbootweb.web;

import cn.zyf.springboot.springbootweb.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if(user!=null){
            //已经登录，放行
            return true;
        }else{
            //未登录，不能访问main页面
            request.setAttribute("loginErr","您还没有登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
    }
}
