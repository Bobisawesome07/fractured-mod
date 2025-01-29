package com.fofr.entity;

import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

import static com.fofr.FracturedMod.POCKET_DIMENSION_PORTAL;

//big class lol, can't fucking imagine making an entity with a brain bruh
public class PocketDimensionPortal extends Entity implements Ownable {
    private static final String DATA_KEY = "data";
	private NbtCompound data = new NbtCompound();
    private int lifetime = 200;
    @Nullable
	private LivingEntity owner;

    @Nullable
	private UUID ownerUuid;
    public PocketDimensionPortal(EntityType<?> type, World world) {
        super(type, world);
        this.readCustomDataFromNbt(data);
        this.noClip = false;
    }
    public PocketDimensionPortal(World world, double x, double y, double z) {
        this(POCKET_DIMENSION_PORTAL, world);
        this.setPosition(x, y, z);
    }
    public int getLifetime() {
        return this.lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
    public void tick(){
        super.tick();
        if(this.getLifetime()<1){
            this.discard();
        }
        else{
            this.setLifetime(this.getLifetime()-1);
        }
    }
    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.data = nbt.getCompound("data");
        this.lifetime = nbt.getInt("Lifetime");
        if (nbt.containsUuid("Owner")) {
			this.ownerUuid = nbt.getUuid("Owner");
		}
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("Lifetime",this.lifetime);
        if (this.ownerUuid != null) {
			nbt.putUuid("Owner", this.ownerUuid);
		}
    }

    @Override
    public @Nullable Entity getOwner() {
        if (this.owner == null && this.ownerUuid != null && this.getWorld() instanceof ServerWorld) {
            Entity entity = ((ServerWorld)this.getWorld()).getEntity(this.ownerUuid);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    public PistonBehavior getPistonBehavior(){
        return PistonBehavior.IGNORE;
    }

    @Override
	public boolean canAvoidTraps() {
		return true;
	}
    public void setOwner(@Nullable LivingEntity owner) {
		this.owner = owner;
		this.ownerUuid = owner == null ? null : owner.getUuid();
	}
}