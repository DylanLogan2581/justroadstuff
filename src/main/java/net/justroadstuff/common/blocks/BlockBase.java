package net.justroadstuff.common.blocks;

import net.justroadstuff.common.JustRoadStuff;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{

    public BlockBase(Material material)
    {
        super(material);
        this.setCreativeTab(JustRoadStuff.JustRoadStuffCreativeTabs);
    }

}
