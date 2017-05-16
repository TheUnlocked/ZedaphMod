package unlocked.zedaphmod.block;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import unlocked.zedaphmod.ZedaphMod;
import unlocked.zedaphmod.tileentity.TileEntityCrystalGrowthChamber;

public class BlockCrystalGrowthChamber extends Block implements ITileEntityProvider {

    public BlockCrystalGrowthChamber(){
        super(Material.IRON);
        this.setCreativeTab(ZedaphMod.ZEDAPHTAB);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }

    public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase user, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, user,stack);
        int dir = user.getHorizontalFacing().getHorizontalIndex();
        world.setBlockState(pos, getStateFromMeta(dir), 2);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {PROPERTYFACING});
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getHorizontal(meta);
        return this.getDefaultState().withProperty(PROPERTYFACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        EnumFacing facing = state.getValue(PROPERTYFACING);

        int facingbits = facing.getHorizontalIndex();
        return facingbits;
    }

    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityCrystalGrowthChamber();
    }

}
