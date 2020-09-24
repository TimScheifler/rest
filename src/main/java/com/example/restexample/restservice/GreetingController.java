package com.example.restexample.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private ArrayList<Greetings> greetingsArrayList = new ArrayList<>();

    @GetMapping()
    public String success(){
        return "success";
    }


    @GetMapping("/greetings")
    public Greetings greeting(@RequestParam(value="name",defaultValue = "World") String name){
        Greetings greetings = new Greetings(counter.incrementAndGet(),String.format(template, name));
        greetingsArrayList.add(greetings);
        return greetings;
    }

    @GetMapping("/all")
    public String getALl(){
        return greetingsArrayListToString(greetingsArrayList);
    }

    private String greetingsArrayListToString(ArrayList<Greetings> greetingsArrayList){
        String allRegisteredUsers = "";
        for(Greetings greetings : greetingsArrayList){
            allRegisteredUsers+=greetings+"\n";
        }
        return allRegisteredUsers;
    }
}
