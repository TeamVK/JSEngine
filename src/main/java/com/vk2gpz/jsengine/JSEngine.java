package com.vk2gpz.jsengine;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.logging.Logger;

public final class JSEngine extends JavaPlugin {
	private final static Logger LOGGER = Logger.getLogger("JSEngine");
	private final static String ENGINE_NAME = "Nashorn";
	private static ScriptEngine ENGINE;

	static {
		ScriptEngineManager manager = new ScriptEngineManager();
		ENGINE = manager.getEngineByExtension("js");
		if (ENGINE == null) {
			ScriptEngineFactory factory = new NashornScriptEngineFactory();
			manager.registerEngineName(ENGINE_NAME, factory);
			ENGINE = manager.getEngineByName(ENGINE_NAME);
		}
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfile = getDescription();
		LOGGER.info(pdfile.getName() + " version " + pdfile.getVersion() + " is Enabled");
	}

	public static ScriptEngine getSharedEngine() {
		return ENGINE;
	}

	public static ScriptEngine getNewEngine() {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("js");
		if (engine == null) {
			ScriptEngineFactory factory = new NashornScriptEngineFactory();
			manager.registerEngineName(ENGINE_NAME, factory);
			engine = manager.getEngineByName(ENGINE_NAME);
		}
		return engine;
	}
}
