# JSEngine
Oracle is going to drop JavaScript engine from java distribution.
However, some plugins do use JavaScript engine to process various JavaScript code.

This plugin simply wraps openjdk's nashorn JavaScript engine and makes it available to other plugins.

To use JavaScript engine, you could do something like:

```
To use JavaScript engine, you could do something like:

1) use JSEngine.jar as a library
[code]
import com.vk2gpz.jsengine.JSEngine;
...
public class Foo {
    void bar() {
        ScriptEngine engine = JSEngine.getNewEngine();
        try {
            engine.eval("your script");
        } catch (javax.script.ScriptException ex) {
            ex.printStackTrace();
        }
...
}
```

2) use JSEngine as a plugin
```
Plugin plugin = Bukkit.getPluginManager().getPlugin("JSEngine");
if (plugin != null) {
   engine = (ScriptEngine) ((JSEngine) plugin).getSharedEngine();
   //engine = (ScriptEngine) ((JSEngine) plugin).getNewEngine();
}
...
if (engine != null) {
   try {
       engine.eval("...you script");
   } catch (javax.script.ScriptException ex) {
       ex.printStackTrace();
   }
}
```

3) use JSEngine through Bukkit's ServicesManager
```
RegisteredServiceProvider<ScriptEngineManager> servicesManager = Bukkit.getServer().getServicesManager().getRegistration(ScriptEngineManager.class);
ScriptEngineManager scriptEngineManager = servicesManager.getProvider();
ScriptEngine engine = scriptEngineManager.getEngineByName("js");
try {
    engine.eval("your script");
} catch (javax.script.ScriptException ex) {
    ex.printStackTrace();
}
```
