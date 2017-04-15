package unlocked.zedaphmod.client.renderer.entity;

import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unlocked.zedaphmod.ZedaphMod;
import unlocked.zedaphmod.entity.EntityFlashmite;

@SideOnly(Side.CLIENT)
public class RenderFlashmite extends RenderLiving<EntityFlashmite>
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(ZedaphMod.MODID, "textures/entity/flashmite.png");

    public RenderFlashmite(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEnderMite(), 0.3F);
    }

    protected float getDeathMaxRotation(EntityEndermite entityLivingBaseIn)
    {
        return 180.0F;
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityFlashmite entity) {
		return TEXTURES;
	}
}