package io.github.bobisawesome07.item.custom

import io.github.bobisawesome07.FracturedMod
import io.github.bobisawesome07.block.ModBlocks
import io.github.bobisawesome07.block.entity.PocketPortalBlockEntity
import io.github.bobisawesome07.world.dimension.ModDimensions.createOrLoadPocketDimension
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

class FracturedSwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings) :
    SwordItem(material, attackDamage, attackSpeed, settings) {
    /**
     * Applies a cooldown to the sword after use
     */
    private fun applyCooldown(user: PlayerEntity, sword: Item) {
        user.itemCooldownManager[sword] = COOLDOWN_TICKS
    }

    /**
     * Creates a portal block at the given position
     */
    private fun createPortal(world: World, blockPos: BlockPos, lookedAtFace: Direction?, playerUuid: UUID) {
        val player = world.getPlayerByUuid(playerUuid)
        if (lookedAtFace == null || !world.getBlockState(blockPos).isAir || !world.getBlockState(blockPos).isReplaceable) {
            return
        }

        LOGGER.info("Setting portal block at $blockPos")
        world.setBlockState(blockPos, ModBlocks.POCKET_PORTAL!!.defaultState)

        val blockEntity = world.getBlockEntity(blockPos)
        if (blockEntity is PocketPortalBlockEntity) {
            blockEntity.playerUuid = playerUuid
        }
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        if (world.isClient || user.itemCooldownManager.isCoolingDown(this.asItem())) {
            return super.use(world, user, hand)
        }

        LOGGER.info("Fractured Sword Event Triggered")

        // Create or load the player's pocket dimension
        createOrLoadPocketDimension(FracturedMod.MOD_ID, user.uuidAsString)


        // Perform raycast to find where to place portal
        val hitResult = user.raycast(RAYCAST_DISTANCE.toDouble(), 1f, false)

        if (hitResult.type == HitResult.Type.BLOCK) {
            val blockHitResult = hitResult as BlockHitResult
            val blockPos = BlockPos.ofFloored(hitResult.getPos())

            LOGGER.info("Raycast successful, attempting to generate portal block")
            createPortal(world, blockPos, blockHitResult.side, user.uuid)
            applyCooldown(user, this)
        } else {
            LOGGER.info("Raycast missed or hit non-block target")
        }

        return super.use(world, user, hand)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        tooltip.add(
            Text.translatable("item.fractured-mod.fractured_sword.tooltip")
                .formatted(Formatting.GOLD, Formatting.ITALIC, Formatting.BOLD)
        )
        super.appendTooltip(stack, world, tooltip, context)
    }

    companion object {
        /** Logger for this class  */
        val LOGGER: Logger = LoggerFactory.getLogger(FracturedMod.MOD_ID)

        /** Maximum raycast distance for portal placement  */
        private const val RAYCAST_DISTANCE = 10.0f

        /** Cooldown in ticks after using the sword (300 seconds)  */
        private const val COOLDOWN_TICKS = 6000
    }
}