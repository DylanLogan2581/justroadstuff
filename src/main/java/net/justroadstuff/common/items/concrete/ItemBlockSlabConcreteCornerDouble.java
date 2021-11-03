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

package net.justroadstuff.common.items.concrete;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.justroadstuff.common.blocks.concrete.BlockSlabConcreteCornerDouble;
import net.justroadstuff.common.init.JustRoadStuffBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemBlockSlabConcreteCornerDouble extends ItemBlock
{
    private final boolean isFullBlock;
    private final Block theHalfSlab;
    private final Block doubleSlab;

    public ItemBlockSlabConcreteCornerDouble(Block block)
    {
        super(block);
        this.theHalfSlab = JustRoadStuffBlocks.singleSlabConcreteCornerDouble;
        this.doubleSlab = JustRoadStuffBlocks.doubleSlabConcreteCornerDouble;
        if(block == JustRoadStuffBlocks.doubleSlabConcreteCornerDouble)
        {
            this.isFullBlock = true;
        }
        else
        {
            this.isFullBlock = false;
        }
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int metadata)
    {
        return this.field_150939_a.getIcon(2, metadata);
    }

    public int getMetadata(int metadata)
    {
        return metadata;
    }

    public String getUnlocalizedName(ItemStack stack)
    {
        return ((BlockSlabConcreteCornerDouble)theHalfSlab).func_150002_b(stack.getItemDamage());
    }

    // Put the bloc
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
    {
        int direction = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if(direction == 0)
        {
            switch(stack.getItemDamage())
            {
                case 0:
                    stack.setItemDamage(4);
                    break;
                case 1:
                    stack.setItemDamage(5);
                    break;
            }
        }
        else if(direction == 1)
        {
            switch(stack.getItemDamage())
            {
                case 0:
                    stack.setItemDamage(6);
                    break;
                case 1:
                    stack.setItemDamage(7);
            }
        }
        else if(direction == 3)
        {
            switch(stack.getItemDamage())
            {
                case 0:
                    stack.setItemDamage(2);
                    break;
                case 1:
                    stack.setItemDamage(3);
                    break;
            }
        }
        if(this.isFullBlock)
        {
            return super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
        }
        else if(stack.stackSize == 0)
        {
            return false;
        }
        else if(!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }
        else
        {
            Block i1 = world.getBlock(x, y, z);
            int j1 = world.getBlockMetadata(x, y, z);
            int k1 = j1 & 7;
            boolean flag = (j1 & 8) != 0;

            if((side == 1 && !flag || side == 0 && flag) && i1 == this.theHalfSlab && k1 == stack.getItemDamage())
            {
                if(world.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, this.doubleSlab, k1, 3))
                {
                    world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.doubleSlab.stepSound.getStepResourcePath(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getPitch() * 0.8F);
                    --stack.stackSize;
                }
                return true;
            }
            else
            {
                return this.placeDoubleSlabFromTop(stack, player, world, x, y, z, side) ? true : super.onItemUse(stack, player, world, x, y, z, side, par8, par9, par10);
            }
        }
    }

    // Can put the bloc?
    @SideOnly(Side.CLIENT)
    public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer player, ItemStack stack)
    {
        int i1 = x;
        int j1 = y;
        int k1 = z;
        Block id = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        int j2 = meta & 7;
        boolean flag = (meta & 8) != 0;

        if((side == 1 && !flag || side == 0 && flag) && id == this.theHalfSlab && j2 == stack.getItemDamage())
        {
            return true;
        }
        else
        {
            if(side == 0)
            {
                --y;
            }

            if(side == 1)
            {
                ++y;
            }

            if(side == 2)
            {
                --z;
            }

            if(side == 3)
            {
                ++z;
            }

            if(side == 4)
            {
                --x;
            }

            if(side == 5)
            {
                ++x;
            }

            id = world.getBlock(x, y, z);
            meta = world.getBlockMetadata(x, y, z);
            j2 = meta & 7;
            flag = (meta & 8) != 0;

            return id == this.theHalfSlab && j2 == stack.getItemDamage() ? true : super.func_150936_a(world, i1, j1, k1, side, player, stack);
        }
    }

    // Build bloc from upper slab
    private boolean placeDoubleSlabFromTop(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side)
    {
        if(side == 0)
        {
            --y;
        }

        if(side == 1)
        {
            ++y;
        }

        if(side == 2)
        {
            --z;
        }

        if(side == 3)
        {
            ++z;
        }

        if(side == 4)
        {
            --x;
        }

        if(side == 5)
        {
            ++x;
        }

        Block i1 = world.getBlock(x, y, z);
        int j1 = world.getBlockMetadata(x, y, z);
        int k1 = j1 & 7;

        if(i1 == this.theHalfSlab && k1 == stack.getItemDamage())
        {
            if(world.checkNoEntityCollision(this.doubleSlab.getCollisionBoundingBoxFromPool(world, x, y, z)) && world.setBlock(x, y, z, this.doubleSlab, k1, 3))
            {
                world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.doubleSlab.stepSound.getStepResourcePath(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show)
    {
        switch(stack.getItemDamage())
        {
            case 0:
                list.add(I18n.format("desc.slabConcreteCornerDouble.0"));
                break;
            case 1:
                list.add(I18n.format("desc.slabConcreteCornerDouble.1"));
                break;
            default:
                break;
        }
    }
}
