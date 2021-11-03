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

package net.justroadstuff.common.items.asphalt;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.justroadstuff.common.blocks.asphalt.BlockAsphaltCorner2;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockAsphaltCorner2 extends ItemBlock
{
    public ItemBlockAsphaltCorner2(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int metadata)
    {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        return this.field_150939_a.getIcon(2, metadata);
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        int metadata = stack.getItemDamage();
        if(metadata < 0 || metadata >= BlockAsphaltCorner2.subBlock.length)
        {
            metadata = 0;
        }
        return super.getUnlocalizedName() + "." + BlockAsphaltCorner2.subBlock[metadata];
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
    {
        switch(stack.getItemDamage())
        {
            case 0:
                list.add(I18n.format("desc.asphaltCorner2.0"));
                break;
            case 1:
                list.add(I18n.format("desc.asphaltCorner2.1"));
                break;
            case 2:
                list.add(I18n.format("desc.asphaltCorner2.2"));
                break;
            case 3:
                list.add(I18n.format("desc.asphaltCorner2.3"));
                break;
            default:
                break;
        }
    }

}
