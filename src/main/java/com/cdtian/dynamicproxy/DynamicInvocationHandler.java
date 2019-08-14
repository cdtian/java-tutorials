package com.cdtian.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.info("Invoked method: {}", method.getName());
        return 43;
    }

    public static void main(String[] args) {
        Map<String, String> proxyInstance = (Map) Proxy.newProxyInstance(
                DynamicInvocationHandler.class.getClassLoader(), new Class[]
                        {Map.class}, new DynamicInvocationHandler());

        proxyInstance.put("hello", "world");
//        proxyInstance.put("hello1", "world1");
//        String result = proxyInstance.entrySet().stream()
//                .map(entry -> entry.getKey() + ":" + entry.getValue()).collect(Collectors.joining(","));
//        System.out.println(result);
    }

}
