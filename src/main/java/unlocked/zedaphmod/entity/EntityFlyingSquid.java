package unlocked.zedaphmod.entity;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import unlocked.zedaphmod.ZedaphMod;

import javax.annotation.Nullable;

public class EntityFlyingSquid extends EntitySquid {

    static final ResourceLocation LOOT_TABLE = LootTableList.register(new ResourceLocation(ZedaphMod.MODID, "entities/flying_squid"));

    public EntityFlyingSquid(World worldIn) {
        super(worldIn);
        this.setNoGravity(true);
    }

    public void onLivingUpdate()
    {
        this.inWater = true;
        super.onLivingUpdate();
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return LOOT_TABLE;
    }

    public void onEntityUpdate()
    {
        this.setAir(300);
        this.fallDistance = 0;
        super.onEntityUpdate();
    }
}
