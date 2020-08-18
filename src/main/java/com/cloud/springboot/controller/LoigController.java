package com.cloud.springboot.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

/**
 * @author myBin
 * @Title:
 * @Package
 * @Description:
 * @date 2020/8/118:49
 */

@Controller
public class LoigController {

    //@RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    @PostMapping("/admin/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request,
                        HttpSession session){

        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            // 登录成功后重定向到首页，避免重复提交登录
            session.setAttribute("username",username);
            return "redirect:/dashboard.html";
        }else {
            request.setAttribute("msg","用户名密码错误！！！修改");
            return "login";
        }

    }

    /**
     * 指示面板页面
     * @return
     * 暂时使用RquestMapping，后期有数据更换PostMapping
     */
    @RequestMapping("/panel")
    public String panel(){
        // 重定向到指示面板页面，避免重复提交登录
        return "redirect:/dashboard.html";
    }

    @RequestMapping("/admin/loginout")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/login.html";
    }

}
