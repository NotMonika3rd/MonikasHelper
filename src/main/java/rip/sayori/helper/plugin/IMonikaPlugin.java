package rip.sayori.helper.plugin;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;

@SuppressWarnings("unused")
public interface IMonikaPlugin {
    default void handleEventPost(EventBus b,Event e) {}
}
