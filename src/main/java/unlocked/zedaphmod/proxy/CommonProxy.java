package unlocked.zedaphmod.proxy;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import unlocked.zedaphmod.ModBlocks;
import unlocked.zedaphmod.ModItems;
import unlocked.zedaphmod.ZedaphMod;
import unlocked.zedaphmod.entity.*;
import unlocked.zedaphmod.event.SquidSpawnEventHandler;
import unlocked.zedaphmod.tileentity.TileEntityCrystalGrowthChamber;

public class CommonProxy {
	
	int modEntityId = 0;
	
	public void registerEntities(){
        ResourceLocation flashPearl = new ResourceLocation(ZedaphMod.MODID, "flashpearl");
		EntityRegistry.registerModEntity(flashPearl, EntityFlashPearl.class, "flashpearl", modEntityId++, ZedaphMod.instance(), 250, 15, true);

        ResourceLocation flashmite = new ResourceLocation(ZedaphMod.MODID, "flashmite");
		EntityRegistry.registerModEntity(flashmite, EntityFlashmite.class, "flashmite", modEntityId++, ZedaphMod.instance(), 250, 3, true);
		EntityRegistry.registerEgg(flashmite,3158064, 15583774);

        ResourceLocation flyingSquid = new ResourceLocation(ZedaphMod.MODID, "flying_squid");
        EntityRegistry.registerModEntity(flyingSquid, EntityFlyingSquid.class, "flyingsquid", modEntityId++, ZedaphMod.instance(), 250, 3, true);
	}
	
	public void registerRecipes(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.FLASH_PEARL, 1),
				".x.",
				"xPx",
				".x.",
				'x', Items.GLOWSTONE_DUST,
				'P', Items.ENDER_PEARL);
		
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(ModBlocks.COLD_GEL), 3),
				"iii",
				"sss",
				"sss",
				'i', Blocks.ICE,
				's', Items.SLIME_BALL);

        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.CRYSTAL_GROWTH_CHAMBER),
                "QHQ",
                "DCG",
                "QHQ",
                'Q', Blocks.QUARTZ_BLOCK,
                'H', Blocks.HOPPER,
                'D', Blocks.DISPENSER,
                'C', new ItemStack(ModItems.COMMAND_PLATE, 1, 0),
                'G', Blocks.GLASS_PANE);

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.COMMAND_PLATE, 1, 0),
                "BAB",
                "RDC",
                "BAB",
                'B', new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1),
                'A', Items.QUARTZ,
                'R', Items.REPEATER,
                'D', Blocks.REDSTONE_BLOCK,
                'C', Items.COMPARATOR);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.COMMAND_PLATE, 1, 1),
                "BAB",
                "RDC",
                "BAB",
                'B', Blocks.SEA_LANTERN,
                'A', Items.EMERALD,
                'R', Items.REPEATER,
                'D', Blocks.REDSTONE_BLOCK,
                'C', Items.COMPARATOR);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.COMMAND_PLATE, 1, 2),
                "BAB",
                "RDC",
                "BAB",
                'B', Blocks.PURPUR_BLOCK,
                'A', Items.SHULKER_SHELL,
                'R', Items.REPEATER,
                'D', Blocks.REDSTONE_BLOCK,
                'C', Items.COMPARATOR);
	}

	public void registerRenderItems() {
		;
	}

	public void registerRenderEntities() {
		;
	}

    public void registerRenderBlocks() { ; }

	public void registerTileEntities(){
        GameRegistry.registerTileEntity(TileEntityCrystalGrowthChamber.class, new ResourceLocation(ZedaphMod.MODID,"crystal_growth_chamber").toString());
    }

	public void registerEvents() {
	    MinecraftForge.EVENT_BUS.register(new SquidSpawnEventHandler());
    }
}
