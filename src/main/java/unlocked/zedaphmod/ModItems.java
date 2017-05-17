package unlocked.zedaphmod;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unlocked.zedaphmod.item.*;

public class ModItems {
	public static final Item FLASH_PEARL = new ItemFlashPearl()
            .setRegistryName("flash_pearl").setUnlocalizedName("flash_pearl");
	public static final Item RECORD_ZSMA_INTRO = new ItemMusicDisk("zsma_intro", ModSounds.registerSound("record.zsma_intro"))
            .setRegistryName("record_zsma_intro").setUnlocalizedName("record_zsma_intro");
	public static final Item COMMAND_PLATE = new Item(){
        @SideOnly(Side.CLIENT)
        public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
        {
            for (int i = 0; i < 3; i++) {
                subItems.add(new ItemStack(itemIn, 1, i));
            }
        }

        public String getUnlocalizedName(ItemStack stack)
        {
            String[] names = {"orange", "green", "purple"};
            return super.getUnlocalizedName() + "." + names[stack.getMetadata()];
        }
    }.setRegistryName("command_plate").setUnlocalizedName("command_plate").setHasSubtypes(true).setMaxDamage(0).setCreativeTab(ZedaphMod.ZEDAPHTAB);
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler {
		public static final Set<Item> ITEMS = new HashSet<Item>();

		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			registry.registerAll(
					FLASH_PEARL,
                    RECORD_ZSMA_INTRO,
                    COMMAND_PLATE
			);
		}
	}
}
