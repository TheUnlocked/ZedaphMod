package unlocked.zedaphmod;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import unlocked.zedaphmod.block.*;

public class ModBlocks {
	public static final BlockColdGel COLD_GEL = (BlockColdGel) new BlockColdGel()
            .setRegistryName("cold_gel").setUnlocalizedName("cold_gel");
    public static final BlockCrystalGrowthChamber CRYSTAL_GROWTH_CHAMBER = (BlockCrystalGrowthChamber) new BlockCrystalGrowthChamber()
            .setRegistryName("crystal_growth_chamber").setUnlocalizedName("crystal_growth_chamber");
	
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<ItemBlock>();
		
		public static ItemBlock getItemBlockFromBlock(Block block){
			for(ItemBlock itemBlock : ITEM_BLOCKS){
				if (itemBlock.block == block){
					return itemBlock;
				}
			}
			return null;
		}

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			final IForgeRegistry<Block> registry = event.getRegistry();

			registry.registerAll(
			        COLD_GEL,
                    CRYSTAL_GROWTH_CHAMBER
			);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
			final ItemBlock[] items = {
				new ItemBlock(COLD_GEL),
                    new ItemBlock(CRYSTAL_GROWTH_CHAMBER)
            };
			
			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) {
				registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
				ITEM_BLOCKS.add(item);
			}
		}
	}
}
