package com.example._2023_04_20;

import org.springframework.stereotype.Component;

@Component
public class SubjectIml implements Subject{
    @Override
    public void doSomething() {
        System.out.println("SubjectIml is doing something");
    }
}
