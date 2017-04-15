package unlocked.zedaphmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import unlocked.zedaphmod.ZedaphMod;
import unlocked.zedaphmod.entity.EntityFlyingSquid;

public class SquidSpawnEventHandler {
    @SubscribeEvent
    public void squidSpawn(EntityJoinWorldEvent event){
        if (event.getEntity().getClass().equals(EntitySquid.class) && event.getWorld().rand.nextFloat() < ZedaphMod.Config.FLYING_SQUID_RATE){
            EntityFlyingSquid e = new EntityFlyingSquid(event.getWorld());
            e.copyLocationAndAnglesFrom(event.getEntity());
            event.getWorld().spawnEntity(e);
            event.setCanceled(true);
        }
    }
}
