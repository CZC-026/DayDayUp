package com.example._2023_04_20;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class StaticProxy implements Subject{
    @Autowired
    private SubjectIml subjectIml;

//    public StaticProxy(SubjectIml subjectIml){
//        this.subjectIml = subjectIml;
//    }

    @Override
    public void doSomething() {
        System.out.println("Static do something before");
        subjectIml.doSomething();
        System.out.println("Static do something after");
    }
}

@Service
class StaticService {

    @Autowired
    private StaticProxy staticProxy;

    public void execute() {
        staticProxy.doSomething();
    }
}