package vod.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloRest {

    @GetMapping("/hello")
    String sayHello() { return "Hey Universe!"; }

}
