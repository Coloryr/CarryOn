package tschipp.carryon.common.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tschipp.carryon.common.config.CarryOnConfig;

import java.util.ArrayList;
import java.util.List;

public class ListHandler {
    public static List<String> FORBIDDEN_TILES;
    public static List<String> FORBIDDEN_ENTITIES;
    public static List<String> ALLOWED_ENTITIES;
    public static List<String> ALLOWED_TILES;
    public static List<String> FORBIDDEN_STACKING;
    public static List<String> ALLOWED_STACKING;

    public static boolean isForbidden(Block block) {
        String name = block.getRegistryName().toString();
        if (FORBIDDEN_TILES.contains(name))
            return true;
        else {
            boolean contains = false;
            for (String s : FORBIDDEN_TILES) {
                if (s.contains("*")) {
                    if (name.contains(s.replace("*", "")))
                        contains = true;
                }
            }

            return contains;
        }
    }

    public static boolean isForbidden(Entity entity) {
        if (EntityList.getKey(entity) != null) {
            String name = EntityList.getKey(entity).toString();
            boolean contains = FORBIDDEN_ENTITIES.contains(name);
            return contains;
        }

        return true;
    }

    public static boolean isAllowed(Entity entity) {
        if (EntityList.getKey(entity) != null) {
            String name = EntityList.getKey(entity).toString();
            boolean contains = ALLOWED_ENTITIES.contains(name);
            return contains;
        }
        return true;
    }

    public static boolean isStackingForbidden(Entity entity) {
        if (EntityList.getKey(entity) != null) {
            String name = EntityList.getKey(entity).toString();
            boolean contains = FORBIDDEN_STACKING.contains(name);
            return contains;
        }

        return true;
    }

    public static boolean isStackingAllowed(Entity entity) {
        if (EntityList.getKey(entity) != null) {
            String name = EntityList.getKey(entity).toString();
            boolean contains = ALLOWED_STACKING.contains(name);
            return contains;
        }
        return true;
    }

    public static boolean isAllowed(Block block) {
        String name = block.getRegistryName().toString();
        if (ALLOWED_TILES.contains(name))
            return true;
        else {
            boolean contains = false;
            for (String s : ALLOWED_TILES) {
                if (s.contains("*")) {
                    if (name.contains(s.replace("*", "")))
                        contains = true;
                }
            }
            return contains;
        }

    }

    public static void initLists() {
        String[] forbidden = CarryOnConfig.blacklist.forbiddenTiles;
        FORBIDDEN_TILES = new ArrayList<String>();

        for (int i = 0; i < forbidden.length; i++) {
            FORBIDDEN_TILES.add(forbidden[i]);
        }

        String[] forbiddenEntity = CarryOnConfig.blacklist.forbiddenEntities;
        FORBIDDEN_ENTITIES = new ArrayList<String>();

        for (int i = 0; i < forbiddenEntity.length; i++) {
            if (forbiddenEntity[i].contains("*")) {
                String modid = forbiddenEntity[i].replace("*", "");
                for (int k = 0; k < ForgeRegistries.ENTITIES.getKeys().size(); k++) {
                    if (ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString().contains(modid)) {
                        FORBIDDEN_ENTITIES.add(ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString());
                    }
                }
            }
            FORBIDDEN_ENTITIES.add(forbiddenEntity[i]);
        }

        String[] allowedEntities = CarryOnConfig.whitelist.allowedEntities;
        ALLOWED_ENTITIES = new ArrayList<String>();
        for (int i = 0; i < allowedEntities.length; i++) {
            if (allowedEntities[i].contains("*")) {
                String modid = allowedEntities[i].replace("*", "");
                for (int k = 0; k < ForgeRegistries.ENTITIES.getKeys().size(); k++) {
                    if (ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString().contains(modid)) {
                        ALLOWED_ENTITIES.add(ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString());
                    }
                }
            }
            ALLOWED_ENTITIES.add(allowedEntities[i]);
        }

        String[] allowedBlocks = CarryOnConfig.whitelist.allowedBlocks;
        ALLOWED_TILES = new ArrayList<String>();
        for (int i = 0; i < allowedBlocks.length; i++) {
            ALLOWED_TILES.add(allowedBlocks[i]);
        }

        String[] forbiddenStacking = CarryOnConfig.blacklist.forbiddenStacking;
        FORBIDDEN_STACKING = new ArrayList<String>();

        for (int i = 0; i < forbiddenStacking.length; i++) {
            if (forbiddenStacking[i].contains("*")) {
                String modid = forbiddenStacking[i].replace("*", "");
                for (int k = 0; k < ForgeRegistries.ENTITIES.getKeys().size(); k++) {
                    if (ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString().contains(modid)) {
                        FORBIDDEN_STACKING.add(ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString());
                    }
                }
            }
            FORBIDDEN_STACKING.add(forbiddenStacking[i]);
        }

        String[] allowedStacking = CarryOnConfig.whitelist.allowedStacking;
        ALLOWED_STACKING = new ArrayList<String>();
        for (int i = 0; i < allowedStacking.length; i++) {
            if (allowedStacking[i].contains("*")) {
                String modid = allowedStacking[i].replace("*", "");
                for (int k = 0; k < ForgeRegistries.ENTITIES.getKeys().size(); k++) {
                    if (ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString().contains(modid)) {
                        ALLOWED_STACKING.add(ForgeRegistries.ENTITIES.getKeys().toArray()[k].toString());
                    }
                }
            }
            ALLOWED_STACKING.add(allowedStacking[i]);
        }
    }

}
