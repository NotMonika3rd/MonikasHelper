package rip.sayori.helper;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import rip.sayori.helper.plugin.PluginManager;
import rip.sayori.helper.plugin.TestPlugin;

import java.util.Objects;

@SuppressWarnings({"unused"})
@Mod(modid = "monikashelper",name = "Monika's Helper", version = "1.0")
public class ModMain {
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        PluginManager.registerPlugin(TestPlugin.class);
        Loader.instance().getIndexedModList().forEach((_, v) -> {
            try {
                e.getAsmData().getAnnotationsFor(v).forEach((_, vv) -> {
                    if(Objects.equals(vv.getAnnotationName(), "rip.sayori.helper.utils.AutoLoad")){
                        try {
                            Class.forName(vv.getClassName());
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            } catch (NullPointerException _) { }
        });
    }
}
