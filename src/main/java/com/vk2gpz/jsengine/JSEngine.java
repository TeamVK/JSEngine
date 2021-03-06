package com.vk2gpz.jsengine;

import org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JSEngine {
    private JSEngine() {
    }

    private static List<String> ENGINE_NAMEs;
    private static ScriptEngineManager MANAGER;
    private static ScriptEngineFactory FACTORY;
    private static boolean USE_OpenJDK_NASHORN;

    static {
        setNames(new ArrayList<>(
                Arrays.asList(
                        "js",
                        "JS",
                        "javascript",
                        "JavaScript",
                        "ecmascript",
                        "ECMAScript",
                        "nashorn",
                        "Nashorn"
                ))
        );
    }

    static void useOpenJDKNashorn(boolean use) {
        USE_OpenJDK_NASHORN = use;
    }

    static void setNames(List<String> names) {
        if (names.size() > 0) {
            ENGINE_NAMEs = names.stream().collect(Collectors.toList());
            if (MANAGER != null) {
                setFactory(MANAGER);
            }
        }
    }

    private static void setFactory(ScriptEngineManager manager) {
        if (FACTORY == null) {
            for (ScriptEngineFactory factory : manager.getEngineFactories()) {
                if (factory.getLanguageName().equalsIgnoreCase("ECMAScript")) {
                    FACTORY = factory;
                }
            }
            if (FACTORY == null) {
                useOpenJDKNashorn(true);
            }
        }

        if (USE_OpenJDK_NASHORN) {
            if (FACTORY == null || !(FACTORY instanceof NashornScriptEngineFactory)) {
                FACTORY = new NashornScriptEngineFactory();
            }
        }

        ENGINE_NAMEs.stream()
                .forEach(n -> manager.registerEngineName(n, FACTORY));
    }

    static void setScriptEngineManager(ScriptEngineManager manager) {
        MANAGER = manager;
        setFactory(MANAGER);
    }

    public static ScriptEngineManager getScriptEngineManager() {
        if (MANAGER == null) {
            MANAGER = new ScriptEngineManager();
            if (MANAGER.getEngineByName("JavaScript") == null) {
                useOpenJDKNashorn(true);
            }
            setFactory(MANAGER);
        }
        return MANAGER;
    }

    public static ScriptEngine getEngine() {
        return getScriptEngineManager().getEngineByName(ENGINE_NAMEs.get(0));
    }
}
