# JSEngine
Oracle is going to drop JavaScript engine from java distribution.
However, some plugins do use JavaScript engine to process various JavaScript code.

This plugin simply wraps openjdk's nashorn JavaScript engine and makes it available to other plugins.

To use JavaScript engine, you could do something like:

```
ScriptEngine engine = null;
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
