package com.vk2gpz.jsengine;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;
import java.util.logging.Logger;

public final class JSEnginePlugin extends JavaPlugin {
	private final static Logger LOGGER = Logger.getLogger("JSEngine");
	private static ScriptEngine ENGINE;

	static {
		ENGINE = JSEngine.getEngine();
	}

	@Override
	public void onLoad() {
		List<String> engine_names = getConfig().getStringList("script_engine_names");
		JSEngine.setNames(engine_names);

		ServicesManager servicesManager = getServer().getServicesManager();
		ScriptEngineManager scriptEngineManager;

		if (servicesManager.isProvidedFor(ScriptEngineManager.class)) {
			RegisteredServiceProvider<ScriptEngineManager> registered = servicesManager.getRegistration(ScriptEngineManager.class);
			scriptEngineManager = registered.getProvider();
		} else {
			scriptEngineManager = JSEngine.getScriptEngineManager();
			servicesManager.register(ScriptEngineManager.class, scriptEngineManager, this, ServicePriority.Highest);
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
		return JSEngine.getEngine();
	}
}
