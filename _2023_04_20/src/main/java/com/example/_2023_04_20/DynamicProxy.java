package com.example._2023_04_20;

import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(){}

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DynamicProxy do something before...");
        Object result = method.invoke(object, args);
        System.out.println("DynamicProxy do something after...");
        return result;
    }
}

@Service
class DynamciService {

    @Autowired
    private SubjectIml subjectIml;

    public void execute() {
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subjectIml.getClass().getClassLoader(),
                subjectIml.getClass().getInterfaces(),
                new DynamicProxy(subjectIml)
        );
        proxy.doSomething();
    }
}



