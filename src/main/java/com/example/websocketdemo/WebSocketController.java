package com.example.websocketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private HttpSession session;


    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public String say( @RequestParam  String name) throws Exception {

        return this.session.getAttribute("username")+"say:"+name;
    }

    @RequestMapping("/ws")
    public  String test(){
        return "ws";
    }
    @RequestMapping("/login")
    public  String login(HttpSession session,@RequestParam String username, @RequestParam String password){
        this.session=session;
        System.out.println("username:"+username+"||password:"+password+"登录成功");
        this.session.setAttribute("username",username);
        return "ws";
    }
    @RequestMapping("/index")
    public  String index(){
        return "login";
    }
}
