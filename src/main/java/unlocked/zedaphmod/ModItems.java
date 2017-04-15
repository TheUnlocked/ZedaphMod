package unlocked.zedaphmod;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import unlocked.zedaphmod.item.*;

public class ModItems {
	public static final Item FLASH_PEARL = new ItemFlashPearl()
            .setRegistryName("flash_pearl").setUnlocalizedName("flash_pearl");
	public static final Item RECORD_ZSMA_INTRO = new ItemMusicDisk("zsma_intro", ModSounds.registerSound("record.zsma_intro"))
            .setRegistryName("record_zsma_intro").setUnlocalizedName("record_zsma_intro");
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<Item> ITEMS = new HashSet<Item>();

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			registry.registerAll(
					FLASH_PEARL,
                    RECORD_ZSMA_INTRO
			);
		}
	}
}
