package unlocked.zedaphmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import unlocked.zedaphmod.proxy.ClientProxy;
import unlocked.zedaphmod.proxy.CommonProxy;

@Mod(modid = ZedaphMod.MODID, version = ZedaphMod.VERSION)
public class ZedaphMod
{
    public static final String MODID = "zedaphmod";
    public static final String VERSION = "1.11.2-0.0.1.2";
    
    @SidedProxy(clientSide = "unlocked.zedaphmod.proxy.ClientProxy", serverSide = "unlocked.zedaphmod.proxy.CommonProxy")
	public static CommonProxy proxy;
    
    @Mod.Instance(ZedaphMod.MODID)
	private static ZedaphMod instance;
    
    public static ZedaphMod instance(){
    	return instance;
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	proxy.registerEntities();
    	proxy.registerRenderItems();
		proxy.registerRenderEntities();
		proxy.registerRenderBlocks();
		Config.loadConfig(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.registerTileEntities();
        proxy.registerEvents();
        proxy.registerRecipes();
        PacketHandler.registerPackets();
    }
    
    public static CreativeTabs ZEDAPHTAB = new CreativeTabs(MODID) {
		
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.FLASH_PEARL);
		}
	};

    public static class Config {

        public static float FLYING_SQUID_RATE;
        public static float FLASHMITE_SPAWN_CHANCE;

        private static void loadConfig(FMLPreInitializationEvent event) {
            Configuration config = new Configuration(event.getSuggestedConfigurationFile());
            config.load();

            FLYING_SQUID_RATE = config.getFloat("flyingSquidReplaceRate", Configuration.CATEGORY_GENERAL, 0.1f, 0.0f, 1.0f,
                    "The percent chance that any squid that spawns will be replaced with a flying squid");
            FLASHMITE_SPAWN_CHANCE = config.getFloat("flashmiteSpawnChance", Configuration.CATEGORY_GENERAL, 0.025f, 0.0f, 1.0f,
                    "The percent chance that a flashmite will spawn as a result of throwing a flash pearl");

            config.save();
        }
    }
}
