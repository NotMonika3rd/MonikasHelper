package rip.sayori.helper.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public record LootTableAccessor(String modid, String name) {
    public ResourceLocation getLocation() {
        return new ResourceLocation(modid, name);
    }

    public List<ItemStack> get(EntityPlayer player) {
        WorldServer world = (WorldServer) player.world;
        return world.getLootTableManager().getLootTableFromLocation(getLocation()).generateLootForPools(world.rand, new LootContext.Builder(world).withPlayer(player).build());
    }

}
