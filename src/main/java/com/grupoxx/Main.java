package com.grupoxx;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {

        public static void main(String[] args) throws Exception {
            ScriptEngineManager factory = new ScriptEngineManager();
            ScriptEngine engine = factory.getEngineByName("Java");

            engine.eval("value = 10");
            Boolean greaterThan5 = (Boolean) engine.eval("value > 5");
            Boolean lessThan5 = (Boolean) engine.eval("value < 5");

            System.out.println(greaterThan5); // true
            System.out.println(lessThan5); // false
        }

}

