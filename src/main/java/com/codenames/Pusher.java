package com.codenames;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class Pusher {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String name) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Hello, " + name + "!";
    }

}