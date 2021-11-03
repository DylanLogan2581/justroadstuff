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

package net.justroadstuff.common.blocks.concrete;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.justroadstuff.common.JustRoadStuff;
import net.justroadstuff.common.init.JustRoadStuffBlocks;
import net.justroadstuff.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSlabConcreteLine extends BlockSlab
{
    public static final String[] StepTypes = new String[] {"simplewhiteline", "simpleyellowline", "doublewhiteline", "doubleyellowline"};
    private IIcon concreteBase, simpleWhiteLine, simpleYellowLine, doubleWhiteLine, doubleYellowLine;

    public BlockSlabConcreteLine(boolean isdouble, Material material)
    {
        super(isdouble, Material.rock);
        this.setCreativeTab(JustRoadStuff.JustRoadStuffCreativeTabs);
        if(!this.field_150004_a)
        {
            this.setLightOpacity(0);
        }
    }

    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.concreteBase = iconRegister.registerIcon(JustRoadStuff.MODID + ":concrete/concreteBase");
        this.simpleWhiteLine = iconRegister.registerIcon(JustRoadStuff.MODID + ":concrete/concreteSWL");
        this.simpleYellowLine = iconRegister.registerIcon(JustRoadStuff.MODID + ":concrete/concreteSYL");
        this.doubleWhiteLine = iconRegister.registerIcon(JustRoadStuff.MODID + ":concrete/concreteDWL");
        this.doubleYellowLine = iconRegister.registerIcon(JustRoadStuff.MODID + ":concrete/concreteDYL");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            if(metadata == 0 || metadata == 4 || metadata == 8 || metadata == 12)
            {
                return this.simpleWhiteLine;
            }
            if(metadata == 1 || metadata == 5 || metadata == 9 || metadata == 13)
            {
                return this.simpleYellowLine;
            }
            if(metadata == 2 || metadata == 6 || metadata == 10 || metadata == 14)
            {
                return this.doubleWhiteLine;
            }
            if(metadata == 3 || metadata == 7 || metadata == 11 || metadata == 15)
            {
                return this.doubleYellowLine;
            }
        }
        if(side == 2 || side == 3)
        {
            if(metadata == 0 | metadata == 8)
            {
                return this.simpleWhiteLine;
            }
            if(metadata == 1 | metadata == 9)
            {
                return this.simpleYellowLine;
            }
            if(metadata == 2 | metadata == 10)
            {
                return this.doubleWhiteLine;
            }
            if(metadata == 3 | metadata == 11)
            {
                return this.doubleYellowLine;
            }
        }
        if(side == 4 || side == 5)
        {
            if(metadata == 4 | metadata == 12)
            {
                return this.simpleWhiteLine;
            }
            if(metadata == 5 | metadata == 13)
            {
                return this.simpleYellowLine;
            }
            if(metadata == 6 | metadata == 14)
            {
                return this.doubleWhiteLine;
            }
            if(metadata == 7 | metadata == 15)
            {
                return this.doubleYellowLine;
            }
        }
        return this.concreteBase;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return ClientProxy.renderSlabAsphaltLineId;
    }

    @SideOnly(Side.CLIENT)
    private static boolean func_150003_a(Block block)
    {
        return block == JustRoadStuffBlocks.singleSlabConcreteLine;
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return func_150003_a(this) ? Item.getItemFromBlock(JustRoadStuffBlocks.singleSlabConcreteLine) : Item.getItemFromBlock(JustRoadStuffBlocks.doubleSlabConcreteLine);
    }

    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        return Item.getItemFromBlock(JustRoadStuffBlocks.singleSlabConcreteLine);
    }

    public int damageDropped(int metadata)
    {
        if(metadata == 4 || metadata == 8 || metadata == 12)
            return 0;
        if(metadata == 5 || metadata == 9 || metadata == 13)
            return 1;
        if(metadata == 6 || metadata == 10 || metadata == 14)
            return 2;
        if(metadata == 7 || metadata == 11 || metadata == 15)
            return 3;
        return metadata;
    }

    protected ItemStack createStackedBlock(int metadata)
    {
        return new ItemStack(JustRoadStuffBlocks.singleSlabConcreteLine, 2, metadata & 7);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list)
    {
        if(item != Item.getItemFromBlock(JustRoadStuffBlocks.doubleSlabConcreteLine))
        {
            for(int i = 0; i < StepTypes.length; i++)
            {
                list.add(new ItemStack(item, 1, i));
            }
        }
    }

    @Override
    public String func_150002_b(int metadata)
    {
        if(metadata < 0 || metadata >= StepTypes.length)
        {
            metadata = 0;
        }
        return super.getUnlocalizedName() + "." + StepTypes[metadata];
    }
}
