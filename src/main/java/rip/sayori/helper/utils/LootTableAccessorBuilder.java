package rip.sayori.helper.utils;

import java.util.ArrayList;
import java.util.List;

public record LootTableAccessorBuilder(String modid) {
    public static final List<LootTableAccessor> ACCESSORS = new ArrayList<>();

    public LootTableAccessor build(String name){
        LootTableAccessor accessor = new LootTableAccessor(modid, name);
        ACCESSORS.add(accessor);
        return accessor;
    }
}
