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
    private static List<String> ENGINE_NAMEs;
    private static ScriptEngineManager MANAGER;
    private static ScriptEngineFactory FACTORY;

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

    static void setNames(List<String> names) {
        if (names.size() > 0) {
            ENGINE_NAMEs = names.stream().collect(Collectors.toList());
            init();
        }
    }

    private static void init() {
        MANAGER = new ScriptEngineManager();
        if (FACTORY == null) {
            FACTORY = new NashornScriptEngineFactory();
        }
        ENGINE_NAMEs.stream()
                .forEach(n -> MANAGER.registerEngineName(n, FACTORY));
    }

    public static ScriptEngineManager getScriptEngineManager() {
        if (MANAGER == null) {
            init();
        }
        return MANAGER;
    }

    public static ScriptEngine getEngine() {
        return getScriptEngineManager().getEngineByName(ENGINE_NAMEs.get(0));
    }
}
