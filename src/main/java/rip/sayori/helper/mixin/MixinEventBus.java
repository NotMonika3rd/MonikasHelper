package rip.sayori.helper.mixin;

import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rip.sayori.helper.plugin.IMonikaPlugin;
import rip.sayori.helper.plugin.PluginManager;

@Mixin(EventBus.class)
public class MixinEventBus {
    @Inject(
            method = "post",
            at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/common/eventhandler/Event;isCancelable()Z")
    )
    private void monikashelper$post(Event event, CallbackInfoReturnable<Boolean> cir) {
        for(IMonikaPlugin plugin : PluginManager.plugins) plugin.handleEventPost((EventBus) (Object) this, event);
    }
}
