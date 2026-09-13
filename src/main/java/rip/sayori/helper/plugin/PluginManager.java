package rip.sayori.helper.plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PluginManager {
    public static final List<IMonikaPlugin> plugins = new ArrayList<>();

    public static void registerPlugin(Class<? extends IMonikaPlugin> plugin) {
        try {
            plugins.add(plugin.getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
