package unlocked.zedaphmod.tileentity;

import javafx.geometry.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.lwjgl.Sys;
import unlocked.zedaphmod.ModBlocks;
import unlocked.zedaphmod.PacketHandler;
import unlocked.zedaphmod.block.BlockCrystalGrowthChamber;

public class TileEntityCrystalGrowthChamber extends TileEntity implements ITickable, ISidedInventory
{
    private int NEEDED_SUGAR = 6;
    private int QUARTZ_OUT = 4;
    private int CONVERT_TICKS = 600;
    public float waterLevel;
    int ticksLeft;
    int sugarIn;
    int quartzIn;
    public boolean sandIn;

    public TileEntityCrystalGrowthChamber(){}

    public void update()
    {
        BlockPos offsetPos = pos.add(world.getBlockState(pos).getValue(BlockCrystalGrowthChamber.PROPERTYFACING).getDirectionVec());
        if (world.getBlockState(offsetPos).getBlock() == Blocks.WATER &&
                world.getBlockState(offsetPos).getBlock().getMetaFromState(world.getBlockState(offsetPos)) == 0 &&
                ticksLeft <= 0) {
            if (!world.isRemote)
                world.setBlockToAir(offsetPos);
            this.ticksLeft = CONVERT_TICKS;
        }
        if (sandIn && ticksLeft > 0 && sugarIn == NEEDED_SUGAR && quartzIn + QUARTZ_OUT <= 64)
        {
            if (--ticksLeft == 0){
                sandIn = false;
                sugarIn = 0;
                quartzIn += 4;
                pushUpdate();
            }
        }
        pushRenderUpdate();
        waterLevel = ticksLeft / (float) CONVERT_TICKS;
    }

    void pushRenderUpdate(){
        if (!world.isRemote){
            world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
    }

    void pushUpdate(){
        if (!world.isRemote){
            world.markAndNotifyBlock(pos, null, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        ticksLeft = compound.getInteger("waterTicksLeft");
        sugarIn = compound.getInteger("sugarIn");
        sandIn = compound.getBoolean("sandIn");
        quartzIn = compound.getInteger("quartzIn");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("waterTicksLeft", ticksLeft);
        compound.setInteger("sugarIn", sugarIn);
        compound.setBoolean("sandIn", sandIn);
        compound.setInteger("quartzIn", quartzIn);
        return super.writeToNBT(compound);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        SPacketUpdateTileEntity packet = new SPacketUpdateTileEntity(pos, 0, writeToNBT(new NBTTagCompound()));
        return packet;
    }

    public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt)
    {
        if (world.isRemote)
            readFromNBT(pkt.getNbtCompound());
    }

    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }

    public void handleUpdateTag(NBTTagCompound tag)
    {
        this.readFromNBT(tag);
    }


    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.UP)
            return new int[]{0};
        if (side == EnumFacing.DOWN)
            return new int[]{1};
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        if (direction == EnumFacing.UP) {
            if (itemStackIn.getItem() == Items.SUGAR && sugarIn <= NEEDED_SUGAR) {
                return true;
            }
            if (itemStackIn.getItem() == Item.getItemFromBlock(Blocks.SAND) && !sandIn) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN) {
            return quartzIn > 0;
        }
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return new ItemStack(Items.QUARTZ, quartzIn);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (quartzIn >= count){
            quartzIn -= count;
            return new ItemStack(Items.QUARTZ, count);
        }
        else if (quartzIn > 0){
            int out = quartzIn;
            quartzIn = 0;
            return new ItemStack(Items.QUARTZ, out);
        }
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index == 1) {
            ItemStack out = new ItemStack(Items.QUARTZ, quartzIn);
            quartzIn = 0;
            return out;
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index == 0){
            if (stack.getItem() == Items.SUGAR) {
                sugarIn += stack.getCount();
                if (sugarIn > NEEDED_SUGAR)
                    sugarIn = NEEDED_SUGAR;
            }
            if (stack.getItem() == Item.getItemFromBlock(Blocks.SAND)) {
                sandIn = true;
            }
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        Item item = stack.getItem();
        return (item == Items.SUGAR && sugarIn < NEEDED_SUGAR) || (item == ItemBlock.getItemFromBlock(Blocks.SAND) && !sandIn);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {}

    @Override
    public String getName() {
        return "crystal_growth_chamber";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }
}
