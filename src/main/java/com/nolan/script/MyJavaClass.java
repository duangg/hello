package com.nolan.script;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.Arrays;

public class MyJavaClass {

    public static String sayHello(String name) {
        return String.format("Hello %s from Java!", name);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void mirror(ScriptObjectMirror mirror) {
        System.out.println(mirror.getClassName() + ":" +
                Arrays.toString(mirror.getOwnKeys(true)));
    }

}