package com.feather.cloud;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFeatherClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) {
        String classPath = "file:///G:/Hello.xlass";

        byte[] bytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(classPath));
            bytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }

        Class clazz = defineClass(name, bytes, 0, bytes.length);
        return clazz;
    }

    public static void main(String[] args) {
        MyFeatherClassLoader classLoader = new MyFeatherClassLoader();
        Class<?> myClass = classLoader.findClass("Hello");
        try {
            Object obj = myClass.newInstance();
            Method method = myClass.getMethod("hello");
            method.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
