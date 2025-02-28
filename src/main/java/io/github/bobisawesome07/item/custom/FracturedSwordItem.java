package io.github.bobisawesome07.item.custom;

import io.github.bobisawesome07.block.ModBlocks;
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity;
import io.github.bobisawesome07.world.dimension.ModDimensions;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import static io.github.bobisawesome07.FracturedMod.MOD_ID;

public class FracturedSwordItem extends Item {
    // Reset the cooldown of the item
    private void resetCooldown(PlayerEntity user, Item sword){
        user.getItemCooldownManager().set(sword, 3000);
    }
    public FracturedSwordItem(Settings settings) {
        super(settings);
    }
    // Initialize logger
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    // Check if the block at the given position is air
    private boolean isAir(World world, BlockPos blockPos){
        return(world.getBlockState(blockPos).getBlock()==Blocks.AIR||world.getBlockState(blockPos).getBlock()==Blocks.CAVE_AIR);
    }
    // Create a portal block at the given position
    private void createPortal(World world, BlockPos blockPos, Direction lookedAtFace, UUID playerUuid){
        if (lookedAtFace != null) {
            if (world.getBlockState(blockPos).isAir()) {  // Or other condition
                LOGGER.info("Setting portal block at " + blockPos);
                world.setBlockState(blockPos, ModBlocks.POCKET_PORTAL.getDefaultState());
                if (world.getBlockEntity(blockPos) instanceof PocketPortalBlockEntity portalEntity) {
                    portalEntity.setPlayerUuid(playerUuid);
                }
            }
        }
    }
    // Override the use method to create a portal block when the item is used
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()&&!user.getItemCooldownManager().isCoolingDown(this.asItem())) {
            LOGGER.info("Fractured Sword Event Triggered");

            ModDimensions.createOrLoadPocketDimension("fractured-mod", user.getUuidAsString(),user.getServer());
            HitResult hitResult= user.raycast(10.0,1f,false);
            BlockHitResult blockHitResult=(BlockHitResult)hitResult;
            BlockPos blockPos = BlockPos.ofFloored(hitResult.getPos());
            LOGGER.info("Raycast successful, attempting to gen portal block");
                createPortal(world, blockPos, blockHitResult.getSide(), user.getUuid());
                resetCooldown(user, this);
            }
            else{
                LOGGER.info("Raycast missed");
            }
        return super.use(world, user, hand);
    }
    // Add tooltip to the item
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.fractured_sword.tooltip").formatted(Formatting.GOLD).formatted(Formatting.ITALIC).formatted(Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }
}