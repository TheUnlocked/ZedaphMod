package unlocked.zedaphmod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSounds {
    public static SoundEvent registerSound(String soundName) {
        final ResourceLocation soundID = new ResourceLocation(ZedaphMod.MODID, soundName);
        return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
    }
}
