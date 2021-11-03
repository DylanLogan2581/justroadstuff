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
import net.justroadstuff.common.Reference;
import net.justroadstuff.common.JustRoadStuff;
import net.justroadstuff.common.tiles.TileEntityBlockTrafficSign;
import net.justroadstuff.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrafficSign extends Block
{
    public static String[] subBlock = new String[] {"signpost", "sign"};

    // Sign textures: square - circle - triangle - diamond - rectangle - misc.

    private IIcon signDiamond[] = new IIcon[Reference.maxSignDiamond];
    private IIcon signSquare[] = new IIcon[Reference.maxSignSquare];
    private IIcon signCircle[] = new IIcon[Reference.maxSignCircle];
    private IIcon signTriangle[] = new IIcon[Reference.maxSignTriangle];
    private IIcon signRectangle[] = new IIcon[Reference.maxSignRectangle];
    private IIcon signMisc[] = new IIcon[Reference.maxSignMisc];
    private IIcon signMiscB[] = new IIcon[Reference.maxSignMiscB];

    private IIcon signPost, signBase, signError;

    public BlockTrafficSign()
    {
        super(Material.iron);
        this.setCreativeTab(JustRoadStuff.JustRoadStuffCreativeTabs);
    }

    public int damageDropped(int metadata)
    {
        return metadata;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return ClientProxy.renderSignPostId;
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i < subBlock.length; i++)
        {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for(int i = 0; i < Reference.maxSignDiamond; i++)
        {
            this.signDiamond[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/diamond/diamond" + i);
        }
        for(int i = 0; i < Reference.maxSignSquare; i++)
        {
            this.signSquare[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/square/square" + i);
        }
        for(int i = 0; i < Reference.maxSignCircle; i++)
        {
            this.signCircle[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/circle/circle" + i);
        }
        for(int i = 0; i < Reference.maxSignTriangle; i++)
        {
            this.signTriangle[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/triangle/triangle" + i);
        }
        for(int i = 0; i < Reference.maxSignRectangle; i++)
        {
            this.signRectangle[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/rectangle/rectangle" + i);
        }
        for(int i = 0; i < Reference.maxSignMisc; i++)
        {
            this.signMisc[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/misc/misc" + i);
        }
        for(int i = 0; i < Reference.maxSignMiscB; i++)
        {
            this.signMiscB[i] = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/misc/misc" + i + "B");
        }

        this.signPost = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/signPost");
        this.signBase = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/signBase");
        this.signError = iconRegister.registerIcon(JustRoadStuff.MODID + ":sign/signError");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 3)
        {
            if(metadata != 0)
                return this.signBase;
        }
        return this.signPost;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        if(world.getBlockMetadata(x, y, z) == 1)
        {
            TileEntity tile = world.getTileEntity(x, y, z);
            if(tile instanceof TileEntityBlockTrafficSign)
            {
                TileEntityBlockTrafficSign tileEntity = (TileEntityBlockTrafficSign)tile;
                short signFace = 0;
                short signBack = 0;
                switch(((TileEntityBlockTrafficSign)tile).getSignDirection())
                {
                    case 0:
                        signFace = 3;
                        signBack = 2;
                        break;
                    case 1:
                        signFace = 4;
                        signBack = 5;
                        break;
                    case 2:
                        signFace = 2;
                        signBack = 3;
                        break;
                    case 3:
                        signFace = 5;
                        signBack = 4;
                        break;
                    default:
                        signFace = 3;
                        signBack = 2;
                        break;
                }

                if(side == signBack)
                {
                    short type = ((TileEntityBlockTrafficSign)tile).getSignType();
                    byte shape = ((TileEntityBlockTrafficSign)tile).getSignShape();
                    if(side == signBack)
                    {
                        if(type == 0)
                            return this.signBase;

                    }
                    if(shape == 5)
                    {
                        if(type < 5)
                            return this.signMiscB[1];
                        if(type >= 5 && type <= 8)
                            return this.signMiscB[2];
                        if(type >= 9 && type <= 32)
                            return this.signMiscB[3];
                        if(type >= 33 && type <= 40)
                            return this.signMiscB[4];
                        if(type >= 41 && type <= 45)
                            return this.signMiscB[5];
                        if(type >= 46 && type <= 47)
                            return this.signMiscB[6];
                        if(type >= 48 && type <= 50)
                            return this.signMiscB[7];
                        if(type > 50)
                            return this.signMiscB[8];
                    }
                }
                if(side == signFace)
                {
                    short type = ((TileEntityBlockTrafficSign)tile).getSignType();
                    byte shape = ((TileEntityBlockTrafficSign)tile).getSignShape();

                    if(type == 0)
                        return this.signBase;

                    switch(shape)
                    {
                        case 0:
                            return this.signSquare[type];
                        case 1:
                            return this.signCircle[type];
                        case 2:
                            return this.signTriangle[type];
                        case 3:
                            return this.signDiamond[type];
                        case 4:
                            return this.signRectangle[type];
                        case 5:
                            return this.signMisc[type];
                        default:
                            return this.signError;
                    }
                }
            }
        }
        return this.signPost;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityBlockTrafficSign)
        {
            byte signColTop = 16;
            if(((TileEntityBlockTrafficSign)tile).getSignShape() == 5 && ((TileEntityBlockTrafficSign)tile).getSignType() > 32 && ((TileEntityBlockTrafficSign)tile).getSignType() < 41 )
                signColTop = 8;
            
            switch(((TileEntityBlockTrafficSign)tile).getSignDirection())
            {
                case 0:
                    this.minX = 0.4F;
                    this.minY = 0.0F;
                    this.minZ = 0.5F;
                    this.maxX = 0.6F;
                    this.maxY = signColTop / 16F;
                    this.maxZ = 0.65F;

                    if(world.getBlockMetadata(x, y, z) == 1)
                    {
                        this.minX = 0.0F;
                        this.maxX = 1F;
                        this.maxZ = 0.7F;
                    }
                    break;
                case 1:
                    this.minX = 0.35F;
                    this.minY = 0.0F;
                    this.minZ = 0.4F;
                    this.maxX = 0.5F;
                    this.maxY = signColTop / 16F;
                    this.maxZ = 0.6F;

                    if(world.getBlockMetadata(x, y, z) == 1)
                    {
                        this.minZ = 0.0F;
                        this.minX = 0.3F;
                        this.maxZ = 1F;
                    }
                    break;
                case 2:
                    this.minX = 0.4F;
                    this.minY = 0.0F;
                    this.minZ = 0.35F;
                    this.maxX = 0.6F;
                    this.maxY = signColTop / 16F;
                    this.maxZ = 0.5F;

                    if(world.getBlockMetadata(x, y, z) == 1)
                    {
                        this.minX = 0.0F;
                        this.maxX = 1F;
                        this.maxZ = 0.5F;
                    }
                    break;
                case 3:
                    this.minX = 0.5F;
                    this.minY = 0.0F;
                    this.minZ = 0.4F;
                    this.maxX = 0.65F;
                    this.maxY = signColTop / 16F;
                    this.maxZ = 0.6F;

                    if(world.getBlockMetadata(x, y, z) == 1)
                    {
                        this.minZ = 0.0F;
                        this.maxX = 0.7F;
                        this.maxZ = 1F;
                    }
                    break;
                default:
                    this.minX = 0.3F;
                    this.minY = 0.0F;
                    this.minZ = 0.5F;
                    this.maxX = 0.7F;
                    this.maxY = 1F;
                    this.maxZ = 0.65F;

                    if(world.getBlockMetadata(x, y, z) == 1)
                    {
                        this.minX = 0.0F;
                        this.maxX = 1F;
                        this.maxZ = 0.7F;
                    }
                    break;
            }
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityBlockTrafficSign();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityBlockTrafficSign)
        {
            int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
            ((TileEntityBlockTrafficSign)tile).setSignDirection((byte)direction);
        }

    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.getBlockMetadata(x, y, z) == 1)
        {
            if(!world.isRemote)
            {
                TileEntity tile = world.getTileEntity(x, y, z);
                if(tile instanceof TileEntityBlockTrafficSign)
                {
                    TileEntityBlockTrafficSign tileEntity = (TileEntityBlockTrafficSign)tile;
                }
                return true;
            }
            else
            {
                if(world.getBlockMetadata(x, y, z) != 0)
                    player.openGui(JustRoadStuff.instance, 0, world, x, y, z);
                return true;
            }
        }
        return false;
    }
}
