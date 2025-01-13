package com.fofr;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class FracturedMaterial implements ToolMaterial {
    public static final FracturedMaterial INSTANCE = new FracturedMaterial();

    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 8.0f;
    }

    @Override
    public float getAttackDamage() {
        return 10;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }


    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.FRACTURED_SHARD);
    }
}
