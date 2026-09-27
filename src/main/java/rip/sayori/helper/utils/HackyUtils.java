package rip.sayori.helper.utils;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import rip.sayori.helper.ModMain;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URLConnection;

@SuppressWarnings({"unused", "removal"})
public class HackyUtils {
    public static final Unsafe UNSAFE;
    public static Instrumentation instrumentation;

    static {
        try {
            Unsafe found = null;
            for (Field field : Unsafe.class.getDeclaredFields()) {
                if (field.getType() != Unsafe.class) continue;
                field.setAccessible(true);
                found = (Unsafe)field.get(null);
                break;
            }
            if (found == null) {
                throw new IllegalStateException("Can't find instance of sun.misc.Unsafe");
            }
            UNSAFE = found;
        }
        catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
        doAttach();
    }

    private static void allowAttachSelf() {
        System.setProperty("jdk.attach.allowAttachSelf", "true");
        try {
            Class<?> vmClass = Class.forName("sun.tools.attach.HotSpotVirtualMachine");
            Field allowAttachSelfField = vmClass.getDeclaredField("ALLOW_ATTACH_SELF");
            Object base = UNSAFE.staticFieldBase(allowAttachSelfField);
            long offset = UNSAFE.staticFieldOffset(allowAttachSelfField);
            UNSAFE.putBoolean(base, offset, true);
        }
        catch (ClassNotFoundException | NoSuchFieldException _) { }
    }

    public static String findSelf(){
        try {
            URLConnection urlConnection = ModMain.class.getProtectionDomain().getCodeSource().getLocation().openConnection();
            if(urlConnection instanceof JarURLConnection jarURLConnection){
                return jarURLConnection.getJarFileURL().getPath();
            }
            return urlConnection.getURL().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doAttach(){
        allowAttachSelf();
        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        try {
            VirtualMachine vm = VirtualMachine.attach(pid);
            String path = System.getProperty("rip.sayori.helper.path", findSelf());
            System.out.println(path);
            if(path.startsWith("/"))path = path.substring(1);
            vm.loadAgent(path);
            vm.detach();
        } catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException e) {
            throw new RuntimeException(e);
        }
    }
}
