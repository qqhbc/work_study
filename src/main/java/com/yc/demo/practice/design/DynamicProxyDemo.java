package com.yc.demo.practice.design;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: yinchao
 * @date 2019/7/4
 */
interface RealObject{
    void run();
}
class RealObjectImpl implements RealObject{

    @Override
    public void run() {
        System.out.println("我要开始跑步！！");
    }
}
class ProxyObject implements InvocationHandler{
    private RealObject realObject;

    public ProxyObject(RealObject realObject){
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("准备一下");
        Object invoke = method.invoke(realObject, args);
        System.out.println("完成啦！");
        return invoke;
    }
}
public class DynamicProxyDemo {
    public static void main(String[] args) {
        RealObjectImpl realObject = new RealObjectImpl();
        RealObject o = (RealObject) Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), new ProxyObject(realObject));
        o.run();
    }
}
