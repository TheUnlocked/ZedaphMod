package unlocked.zedaphmod.proxy;

import net.minecraft.client.renderer.entity.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import unlocked.zedaphmod.ModBlocks;
import unlocked.zedaphmod.ModItems;
import unlocked.zedaphmod.client.renderer.entity.RenderFlashmite;
import unlocked.zedaphmod.entity.EntityFlashPearl;
import unlocked.zedaphmod.entity.EntityFlashmite;
import unlocked.zedaphmod.entity.EntityFlyingSquid;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRenderItems(){	
		ModelLoader.setCustomModelResourceLocation(ModItems.FLASH_PEARL, 0, new ModelResourceLocation(ModItems.FLASH_PEARL.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ModItems.RECORD_ZSMA_INTRO, 0, new ModelResourceLocation(ModItems.RECORD_ZSMA_INTRO.getRegistryName(), "inventory"));
		
		for (ItemBlock item : ModBlocks.RegistrationHandler.ITEM_BLOCKS){
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
	
	@Override
	public void registerRenderBlocks(){	
		
	}
	
	@Override
	public void registerRenderEntities(){		
		RenderingRegistry.registerEntityRenderingHandler(EntityFlashPearl.class, rm -> new RenderSnowball(rm, ModItems.FLASH_PEARL, Minecraft.getMinecraft().getRenderItem()));
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFlashmite.class, RenderFlashmite::new);
	}
}
