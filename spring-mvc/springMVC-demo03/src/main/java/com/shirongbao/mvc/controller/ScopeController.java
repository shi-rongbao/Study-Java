package com.shirongbao.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ShiRongbao
 * @Create 2024/2/5 15:21
 */
@Controller
public class ScopeController {

    // 使用servletAPI向request域对象共享数据
    @RequestMapping("/testRequestByServletAPI")
    public String testRequestByServletAPI(HttpServletRequest request) {
        // setAttribute设置共享数据
        request.setAttribute("fuck", "这是fuck");
        return "success";
    }

    // 使用ModelAndView往请求域中共享数据
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        // 首先要获取ModelAndView对象
        ModelAndView mav = new ModelAndView();
        // 通过ModelAndView往request域对象中共享数据
        mav.addObject("fuck", "这是modelAndView的");
        // 设置视图名称
        mav.setViewName("success");
        return mav;
    }

    // 使用Model往请求域中共享数据
    @RequestMapping("/testModel")
    public String testModel(Model model) {
        model.addAttribute("fuck", "这是model的");
        System.out.println(model.getClass().getName());
        return "success";
    }

    // 使用Map集合往请求域中贡献数据
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        map.put("fuck", "这是map的");
        System.out.println(map.getClass().getName());
        return "success";
    }

    // 使用ModelMap往请求域中共享数据
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("fuck", "这是modelMap的");
        System.out.println(modelMap.getClass().getName());
        return "success";
    }

    // 使用原生ServletAPI往session域对象中共享数据
    @RequestMapping("/testSession")
    public String testSession(HttpSession session) {
        session.setAttribute("sessionFuck", "这是原生servlet往session域对象中共享数据");
        return "success";
    }

    // 通过原生ServletAPI往context域对象中共享数据
    @RequestMapping("/testContext")
    public String testContext(HttpSession session) {
        // 首先获取到ServletContext对象
        ServletContext application = session.getServletContext();
        application.setAttribute("contextFuck", "这是原生ServletAPI往context域对象中共享数据");
        return "success";
    }

}
