/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;

import com.me.dao.LoginDAO;
import com.me.pojo.Login;
import com.me.validator.LoginValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Nirali
 */
public class LoginController extends SimpleFormController {
    
    public LoginController() {
        //Initialize controller properties here or 
        //in the Web Application Context
        
        setCommandClass(Login.class);
        setCommandName("login");
        setSuccessView("loginsuccess");
        setFormView("loginform");
        setValidator(new LoginValidator());
    }
    
//    @Override
//    protected void doSubmitAction(Object command) throws Exception {
//      
//        Login loginUser = (Login) command;
//        //LoginDAO ldao = new LoginDAO();
//        LoginDAO ldao = (LoginDAO) this.getApplicationContext().getBean("logindao");
//
//        if(ldao.checkLogin(loginUser))
//        {
//            
//            //Place the username in the session
//            //and send the user to home page
//            //Some links on the user home page
//            // such as view friends, view messages , send message
//            
//            
//            
//            
//        }
//        else{
//            //No session is created and send the user to lginpage
//        }
//    }

    //Use onSubmit instead of doSubmitAction 
    //when you need access to the Request, Response, or BindException objects
    
    @Override
    protected ModelAndView onSubmit(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object command, 
            BindException errors) throws Exception {
        
        ModelAndView mv = null;
        //Do something...
           Login loginUser = (Login) command;
        //LoginDAO ldao = new LoginDAO();
        LoginDAO ldao = (LoginDAO) this.getApplicationContext().getBean("logindao");
        HttpSession session = request.getSession();
        
        //Needs more work
//        if(request.getRequestURI().endsWith("logout.htm")){
//            session.invalidate();
//            mv = new ModelAndView("error","message","Successfully Logged Out!");
//        }
        System.out.println("in");
        if(ldao.checkLogin(loginUser))
        {
            
            //Place the username in the session
            //and send the user to home page
            //Some links on the user home page
            // such as view friends, view messages , send message
            session.setAttribute("username", loginUser.getUsername());
            mv = new ModelAndView(getSuccessView());
        }
        else{
            //No session is created and send the user to lginpage
            mv = new ModelAndView("error","message","Incorrect Username and Password");

        }
        return mv;
        
        
    }
   
}
