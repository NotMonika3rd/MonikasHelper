package rip.sayori.helper.agent;

import net.minecraft.launchwrapper.Launch;

import java.lang.instrument.Instrumentation;

public class MonikaAgent {
    public static void agentmain(String args, Instrumentation instrumentation) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Launch.classLoader.findClass("rip.sayori.helper.utils.AgentUtils")
                .getField("instrumentation")
                .set(null, instrumentation);
    }
}
