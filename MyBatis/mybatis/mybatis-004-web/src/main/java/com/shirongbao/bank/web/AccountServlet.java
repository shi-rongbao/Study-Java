package com.shirongbao.bank.web;

import com.shirongbao.bank.exception.MoneyNotEnoughException;
import com.shirongbao.bank.exception.TransferException;
import com.shirongbao.bank.service.AccountService;
import com.shirongbao.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取表单数据
        String fromActno = req.getParameter("fromActno");
        String toActno = req.getParameter("toActno");
        double money = Double.parseDouble(req.getParameter("money"));
        // 调用服务层方法进行转账
        // 调用View完成转账结果
        try {
            accountService.transfer(fromActno, toActno, money);
            resp.sendRedirect(req.getContextPath() + "/success.html");
        } catch (MoneyNotEnoughException e) {
            // 余额不足
            resp.sendRedirect(req.getContextPath() + "/error1.html");
        } catch (TransferException e) {
            // 转账异常
            resp.sendRedirect(req.getContextPath() + "/error2.html");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/error2.html");
        }
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 获取表单数据
//        String fromActno = req.getParameter("fromActno");
//        String toActno = req.getParameter("toActno");
//        double money = Double.parseDouble(req.getParameter("money"));
//        // 调用服务层方法进行转账
//        // 调用View完成转账结果
//        try {
//            accountService.transfer(fromActno, toActno, money);
//            resp.sendRedirect(req.getContextPath() + "/success.html");
//        } catch (MoneyNotEnoughException e) {
//            // 余额不足
//            resp.sendRedirect(req.getContextPath() + "/error1.html");
//        } catch (TransferException e) {
//            // 转账异常
//            resp.sendRedirect(req.getContextPath() + "/error2.html");
//        }
//    }
}
