/*
The MIT License (MIT)

Copyright for portions of project JustRoadStuff are held by (c) 2015 KillerMapper as part of project RoadStuff.
All other copyright for project JustRoadStuff are held by (c) 2021 DylanLogan2581.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package net.justroadstuff.common.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.justroadstuff.common.JustRoadStuff;
import net.justroadstuff.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCone extends Block
{
    public static String[] subBlock = new String[] {"cone01", "cone02", "cone03"};
    private IIcon top, sides, bottom, base, top2;

    public BlockCone()
    {
        super(Material.ground);
        this.setCreativeTab(JustRoadStuff.JustRoadStuffCreativeTabs);
        this.setStepSound(soundTypeMetal);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        if(world.getBlockMetadata(x, y, z) == 0)
        {
            this.minX = 0.3F;
            this.minY = 0.0F;
            this.minZ = 0.3F;
            this.maxX = 0.7F;
            this.maxY = 1F;
            this.maxZ = 0.7F;
        }
        if(world.getBlockMetadata(x, y, z) == 2)
        {
            this.minX = 0.44F;
            this.minY = 0.0F;
            this.minZ = 0.44F;
            this.maxX = 0.56F;
            this.maxY = 1F;
            this.maxZ = 0.56F;
        }
        if(world.getBlockMetadata(x, y, z) == 1)
        {
            this.minX = 0.25F;
            this.minY = 0.0F;
            this.minZ = 0.25F;
            this.maxX = 0.75F;
            this.maxY = 0.875F;
            this.maxZ = 0.75F;
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.top = iiconRegister.registerIcon(JustRoadStuff.MODID + ":blockCone01Top");
        this.sides = iiconRegister.registerIcon(JustRoadStuff.MODID + ":blockCone01");
        this.bottom = iiconRegister.registerIcon(JustRoadStuff.MODID + ":blockConeBottom");
        this.base = iiconRegister.registerIcon(JustRoadStuff.MODID + ":blockConeBase");
        this.top2 = iiconRegister.registerIcon(JustRoadStuff.MODID + ":blockCone02Top");
    }

    public int damageDropped(int metadata)
    {
        return metadata;
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i < subBlock.length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return ClientProxy.renderConeId;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(metadata == 2)
        {
            if(side == 1)
                return this.base;
        }
        if(metadata == 0)
        {
            if(side == 1)
                return this.top;
        }
        if(metadata == 1)
        {
            if(side == 1)
                return this.top2;
        }
        if(side == 0)
            return this.bottom;
        return this.sides;
    }

}
