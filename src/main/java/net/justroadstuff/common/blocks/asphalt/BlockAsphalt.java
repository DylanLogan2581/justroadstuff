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

package net.justroadstuff.common.blocks.asphalt;

import java.util.List;

import net.justroadstuff.common.JustRoadStuff;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockAsphalt extends Block
{

    public static String[] subBlockAsphaltBase01 = new String[] {"asphalt", "manhole"};
    private IIcon asphaltBase, sewerManhole;

    public BlockAsphalt()
    {
        super(Material.rock);
        this.setCreativeTab(JustRoadStuff.JustRoadStuffCreativeTabs);
    }

    public int damageDropped(int metadata)
    {
        return metadata;
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i < subBlockAsphaltBase01.length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.asphaltBase = iconRegister.registerIcon(JustRoadStuff.MODID + ":asphalt/asphaltBase");
        this.sewerManhole = iconRegister.registerIcon(JustRoadStuff.MODID + ":asphalt/asphaltManhole");
    }

    public IIcon getIcon(int side, int metadata)
    {
        switch(metadata)
        {
            case 0:
                return this.asphaltBase;
            case 1:
                if(side == 1)
                {
                    return this.sewerManhole;
                }
                return this.asphaltBase;
            default:
                return this.asphaltBase;
        }
    }
}
