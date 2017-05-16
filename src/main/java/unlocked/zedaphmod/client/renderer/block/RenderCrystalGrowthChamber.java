package unlocked.zedaphmod.client.renderer.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.animation.FastTESR;
import unlocked.zedaphmod.tileentity.TileEntityCrystalGrowthChamber;

import javax.annotation.Nonnull;

public class RenderCrystalGrowthChamber extends FastTESR<TileEntityCrystalGrowthChamber>{
    @Override
    public void renderTileEntityFast(@Nonnull TileEntityCrystalGrowthChamber te, double x, double y, double z, float partialTicks, int destroyStage, @Nonnull VertexBuffer vertexBuffer) {

        final float PX = 1f / 16f;
        final float YOFF = 2 * PX;
        final float BORDER = 1.9f * PX;
        final float MAXHEIGHT = 10 * PX;

        BlockModelShapes bm = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
        TextureAtlasSprite sprite;

        // Lightmap calculations
        int upCombined = getWorld().getCombinedLight(te.getPos().up(), 0);
        int upLMa = upCombined >> 16 & 65535;
        int upLMb = upCombined & 65535;

        int northCombined = getWorld().getCombinedLight(te.getPos().add(new BlockPos(0, 0, 1)), 0);
        int northLMa = northCombined >> 16 & 65535;
        int northLMb = northCombined & 65535;

        int southCombined = getWorld().getCombinedLight(te.getPos().add(new BlockPos(0, 0, -1)), 0);
        int southLMa = southCombined >> 16 & 65535;
        int southLMb = southCombined & 65535;

        int westCombined = getWorld().getCombinedLight(te.getPos().add(new BlockPos(-1, 0, 0)), 0);
        int westLMa = westCombined >> 16 & 65535;
        int westLMb = westCombined & 65535;

        int eastCombined = getWorld().getCombinedLight(te.getPos().add(new BlockPos(1, 0, 0)), 0);
        int eastLMa = eastCombined >> 16 & 65535;
        int eastLMb = eastCombined & 65535;

        vertexBuffer.setTranslation(x, y, z);

        if (te.sandIn){
            float actualHeight = (MAXHEIGHT * te.waterLevel / 2) + YOFF;
            float insetBorder = BORDER * 0.99f;
            if (te.waterLevel <= 0)
                actualHeight = (MAXHEIGHT / 2) + YOFF;
            sprite = bm.getTexture(Blocks.SAND.getDefaultState());

            vertexBuffer.pos(insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(upLMa, upLMb).endVertex();

            //NORTH face

            vertexBuffer.pos(insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, YOFF, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(insetBorder, YOFF, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(northLMa, northLMb).endVertex();

            //SOUTH face
            vertexBuffer.pos(insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, YOFF, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(insetBorder, YOFF, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(southLMa, southLMb).endVertex();

            //WEST face
            vertexBuffer.pos(insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(insetBorder, YOFF, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(insetBorder, YOFF, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(westLMa, westLMb).endVertex();

            //EAST face
            vertexBuffer.pos(1 - insetBorder, actualHeight, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, actualHeight, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, YOFF, 1 - insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - insetBorder, YOFF, insetBorder).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(eastLMa, eastLMb).endVertex();
        }

        if (te.waterLevel > 0) {
            sprite = bm.getTexture(Blocks.WATER.getDefaultState());
            float actualHeight = (MAXHEIGHT * te.waterLevel) + YOFF;

            //UP face
            vertexBuffer.pos(BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 0.8f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 0.8f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 0.8f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(upLMa, upLMb).endVertex();
            vertexBuffer.pos(BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 0.8f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(upLMa, upLMb).endVertex();

            sprite = bm.getTexture(Blocks.FLOWING_WATER.getDefaultState());

            //NORTH face

            vertexBuffer.pos(BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, YOFF, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(northLMa, northLMb).endVertex();
            vertexBuffer.pos(BORDER, YOFF, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(northLMa, northLMb).endVertex();

            //SOUTH face
            vertexBuffer.pos(BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, YOFF, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(southLMa, southLMb).endVertex();
            vertexBuffer.pos(BORDER, YOFF, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(southLMa, southLMb).endVertex();

            //WEST face
            vertexBuffer.pos(BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(BORDER, YOFF, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(westLMa, westLMb).endVertex();
            vertexBuffer.pos(BORDER, YOFF, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(westLMa, westLMb).endVertex();

            //EAST face
            vertexBuffer.pos(1 - BORDER, actualHeight, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMinV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, actualHeight, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMinV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, YOFF, 1 - BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(eastLMa, eastLMb).endVertex();
            vertexBuffer.pos(1 - BORDER, YOFF, BORDER).color(1f, 1f, 1f, 1f).tex(sprite.getMinU(), sprite.getMaxV()).lightmap(eastLMa, eastLMb).endVertex();
        }
    }
}
