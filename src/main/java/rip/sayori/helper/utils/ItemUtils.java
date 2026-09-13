package rip.sayori.helper.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

@SuppressWarnings("unused")
public class ItemUtils {
    public static ItemStack compoundSafe(ItemStack item) {
        if (!item.hasTagCompound()) item.setTagCompound(new NBTTagCompound());
        return item;
    }
}
