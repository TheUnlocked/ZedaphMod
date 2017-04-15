package unlocked.zedaphmod.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unlocked.zedaphmod.ZedaphMod;

public class BlockColdGel extends Block
{
    public BlockColdGel()
    {
        super(Material.GRASS);
        this.setCreativeTab(ZedaphMod.ZEDAPHTAB);
        this.slipperiness = 0.8F;
        this.setHardness(1.5f);
        this.setSoundType(SoundType.SLIME);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    /**
     * Called When an Entity Collided with the Block
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        if (entityIn.motionY > 3){
            entityIn.motionY = 3;
        }
    	entityIn.motionY *= 0.1;
        entityIn.fallDistance = 0f;
        entityIn.extinguish();
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {	
		return new AxisAlignedBB(0,0,0,1,0.625,1);
    	
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }


    public boolean isToolEffective(String type, IBlockState state)
    {
    	return type == "shovel";
    }
}