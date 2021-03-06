import com.vk2gpz.jsengine.JSEnginePlugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import static org.junit.Assert.*;

public class JSEnginePluginTest {

    @Test
    public void testJSEngine() {
        ScriptEngine engine = JSEnginePlugin.getSharedEngine();
        try {
            if (engine != null) {
                try {
                    engine.eval("function hello() {\n" +
                            "    print(\"func1\");\n" +
                            "    return \"func1\";\n" +
                            "}");
                    Object result1 = ((Invocable) engine).invokeFunction("hello");
                    assertEquals("func1", result1);

                    engine.eval("function hello() {\n" +
                            "    print(\"func2\");\n" +
                            "    return \"func2\";\n" +
                            "}");
                    Object result2 = ((Invocable) engine).invokeFunction("hello");
                    assertEquals("func2", result2);

                    Object result3 = ((Invocable) engine).invokeFunction("hello");
                    assertEquals("func2", result3);
                } catch (javax.script.ScriptException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }


        try {
            if (engine != null) {
                try {
                    ScriptEngine engine1 = JSEnginePlugin.getNewEngine();
                    engine1.eval("function hello() {\n" +
                            "    print(\"func1\");\n" +
                            "    return \"func1\";\n" +
                            "}");
                    Object result1 = ((Invocable) engine1).invokeFunction("hello");
                    assertEquals("func1", result1);

                    ScriptEngine engine2 = JSEnginePlugin.getNewEngine();
                    engine2.eval("function hello() {\n" +
                            "    print(\"func2\");\n" +
                            "    return \"func2\";\n" +
                            "}");
                    Object result2 = ((Invocable) engine2).invokeFunction("hello");
                    assertEquals("func2", result2);

                    Object result3 = ((Invocable) engine1).invokeFunction("hello");
                    assertEquals("func1", result3);
                    Object result4 =  ((Invocable) engine2).invokeFunction("hello");
                    assertEquals("func2", result4);
                } catch (javax.script.ScriptException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }
}
