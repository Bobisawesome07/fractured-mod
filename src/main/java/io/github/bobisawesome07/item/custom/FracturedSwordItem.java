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
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import java.util.List;
import java.util.UUID;

import static io.github.bobisawesome07.FracturedMod.MOD_ID;

public class FracturedSwordItem extends SwordItem {
    /** Logger for this class */
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    /** Maximum raycast distance for portal placement */
    private static final float RAYCAST_DISTANCE = 10.0f;
    
    /** Cooldown in ticks after using the sword (300 seconds) */
    private static final int COOLDOWN_TICKS = 6000;

    /**
     * Constructs a new FracturedSwordItem with the specified tool material, attack damage, attack speed, and settings.
     *
     * This constructor initializes the custom sword item, configuring its combat and durability attributes
     * by passing the provided values to the superclass constructor.
     *
     * @param material the tool material defining the sword's durability and properties
     * @param attackDamage the base damage the sword inflicts
     * @param attackSpeed the modifier for the sword's attack speed
     * @param settings the item configuration settings
     */
    public FracturedSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Applies a cooldown period to the given sword, preventing its immediate reuse by the player.
     *
     * @param user  the player using the sword.
     * @param sword the sword item to which the cooldown is applied.
     */
    private void applyCooldown(PlayerEntity user, Item sword) {
        user.getItemCooldownManager().set(sword, COOLDOWN_TICKS);
    }

    /**
     * Attempts to create a pocket portal at the specified block position.
     * 
     * <p>This method verifies that the target conditions for portal creation are met before proceeding:
     * it checks that the direction the player looked at is not null and that the block at the given position
     * is both air and replaceable. If these conditions are satisfied, the block state is updated to a pocket portal,
     * and the portal block entity (if applicable) is associated with the player's UUID.</p>
     *
     * @param world the game world where the portal should be created
     * @param blockPos the target position for portal creation
     * @param lookedAtFace the face of the block that was targeted (must be non-null)
     * @param playerUuid the unique identifier of the player associated with the portal
     */
    private void createPortal(World world, BlockPos blockPos, Direction lookedAtFace, UUID playerUuid) {
        PlayerEntity player = world.getPlayerByUuid(playerUuid);
        if (lookedAtFace == null || !world.getBlockState(blockPos).isAir()||!world.getBlockState(blockPos).isReplaceable()) {
            return;
        }
        
        LOGGER.info("Setting portal block at " + blockPos);
        world.setBlockState(blockPos, ModBlocks.POCKET_PORTAL.getDefaultState());
        
        if (world.getBlockEntity(blockPos) instanceof PocketPortalBlockEntity portalEntity) {
            portalEntity.setPlayerUuid(playerUuid);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient || user.getItemCooldownManager().isCoolingDown(this.asItem())) {
            return super.use(world, user, hand);
        }

        LOGGER.info("Fractured Sword Event Triggered");

        // Create or load the player's pocket dimension
        ModDimensions.createOrLoadPocketDimension(MOD_ID, user.getUuidAsString());
        
        // Perform raycast to find where to place portal
        HitResult hitResult = user.raycast(RAYCAST_DISTANCE, 1f, false);
        
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos blockPos = BlockPos.ofFloored(hitResult.getPos());
            
            LOGGER.info("Raycast successful, attempting to generate portal block");
            createPortal(world, blockPos, blockHitResult.getSide(), user.getUuid());
            applyCooldown(user, this);
        } else {
            LOGGER.info("Raycast missed or hit non-block target");
        }
        
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.fractured-mod.fractured_sword.tooltip")
                .formatted(Formatting.GOLD, Formatting.ITALIC, Formatting.BOLD));
        super.appendTooltip(stack, world, tooltip, context);
    }
}