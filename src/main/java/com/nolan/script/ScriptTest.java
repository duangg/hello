package com.nolan.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptTest {
    public static void main(String[] args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        String script = "var MyJavaClass = Java.type('com.nolan.script.ScriptTest');\n" +
                "\n" +
                "// call the static method\n" +
                "var greetingResult = MyJavaClass.greet('John Doe');\n" +
                "print(greetingResult);\n" +
                "MyJavaClass.mirror({\n" +
                "    foo: 'bar',\n" +
                "    bar: 'foo'\n" +
                "});";
        try {
            engine.eval(script);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public static String sayHello(String name) {
        return String.format("Hello %s from Java!", name);
    }

    public static String greet(String name) {
        return String.format("Hello %s from Java!", name);

//        System.out.format("Hi there from Java, %s", name);
//        return "greetings from java";
    }

}
