package unlocked.zedaphmod.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unlocked.zedaphmod.ZedaphMod;

public class EntityFlashPearl extends EntityThrowable
{
    private EntityLivingBase thrower;

    public EntityFlashPearl(World worldIn)
    {
        super(worldIn);
    }

    public EntityFlashPearl(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
        this.thrower = throwerIn;
    }

    @SideOnly(Side.CLIENT)
    public EntityFlashPearl(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesFlashPearl(DataFixer p_189663_0_)
    {
        EntityThrowable.registerFixesThrowable(p_189663_0_, "ThrownFlashpearl");
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (result.entityHit != null)
        {
            if (result.entityHit == this.thrower)
            {
                return;
            }

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, entitylivingbase), 0.0F);
        }

//        if (result.typeOfHit == RayTraceResult.Type.BLOCK)
//        {
//            BlockPos blockpos = result.getBlockPos();
//            TileEntity tileentity = this.world.getTileEntity(blockpos);
//
//            if (tileentity instanceof TileEntityEndGateway)
//            {
//                TileEntityEndGateway tileentityendgateway = (TileEntityEndGateway)tileentity;
//
//                if (entitylivingbase != null)
//                {
//                    tileentityendgateway.teleportEntity(entitylivingbase);
//                    this.setDead();
//                    return;
//                }
//
//                tileentityendgateway.teleportEntity(this);
//                return;
//            }
//        }
        impact(entitylivingbase);
    }
    
    private void impact(EntityLivingBase entitylivingbase){
    	for (int i = 0; i < 32; ++i)
        {
            this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ, this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian(), new int[0]);
        }

        if (!this.world.isRemote)
        {
            if (entitylivingbase instanceof EntityPlayerMP)
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)entitylivingbase;

                if (entityplayermp.connection.getNetworkManager().isChannelOpen() && entityplayermp.world == this.world && !entityplayermp.isPlayerSleeping())
                {
                    net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 2.0F);
                    if (!net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event))
                    {
                        if (this.rand.nextFloat() < ZedaphMod.Config.FLASHMITE_SPAWN_CHANCE && this.world.getGameRules().getBoolean("doMobSpawning"))
                        {
                            EntityFlashmite EntityFlashmite = new EntityFlashmite(this.world);
                            EntityFlashmite.setSpawnedByPlayer(true);
                            EntityFlashmite.setLocationAndAngles(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, entitylivingbase.rotationYaw, entitylivingbase.rotationPitch);
                            this.world.spawnEntity(EntityFlashmite);
                        }

                        if (entitylivingbase.isRiding())
                        {
                            entitylivingbase.dismountRidingEntity();
                        }

                        entitylivingbase.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
                        entitylivingbase.fallDistance = 0.0F;
                        entitylivingbase.attackEntityFrom(DamageSource.FALL, event.getAttackDamage());
                    }
                }
            }
            else if (entitylivingbase != null)
            {
                entitylivingbase.setPositionAndUpdate(this.posX, this.posY, this.posZ);
                entitylivingbase.fallDistance = 0.0F;
            }

            this.setDead();
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        EntityLivingBase entitylivingbase = this.getThrower();

        if (entitylivingbase != null && entitylivingbase instanceof EntityPlayer && !entitylivingbase.isEntityAlive())
        {
            this.setDead();
        }
        else if (entitylivingbase != null && this.getThrower().isSneaking()){
        	impact(entitylivingbase);
        }
        else {
            super.onUpdate();
        }
    }
}