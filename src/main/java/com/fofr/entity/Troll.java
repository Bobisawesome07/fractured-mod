package com.fofr.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class Troll extends PathAwareEntity{
    public Troll (EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType,world);
    }
}