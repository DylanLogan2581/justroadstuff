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

package net.justroadstuff.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.justroadstuff.common.tiles.TileEntityBlockTrafficSign;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;

public class PacketSignType implements IMessage
{
    private short signType;
    private byte signShape;
    private int tileX, tileY, tileZ;

    public PacketSignType()
    {}

    public PacketSignType(short signType, byte signShape, int x, int y, int z)
    {
        this.signType = signType;
        this.signShape = signShape;
        this.tileX = x;
        this.tileY = y;
        this.tileZ = z;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.signType = buf.readShort();
        this.signShape = buf.readByte();
        this.tileX = buf.readInt();
        this.tileY = buf.readInt();
        this.tileZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeShort(this.signType);
        buf.writeByte(this.signShape);
        buf.writeInt(this.tileX);
        buf.writeInt(this.tileY);
        buf.writeInt(this.tileZ);
    }

    public static class Handler implements IMessageHandler<PacketSignType, IMessage>
    {

        @Override
        public IMessage onMessage(PacketSignType message, MessageContext ctx)
        {
            TileEntity tile = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.tileX, message.tileY, message.tileZ);
            if(tile instanceof TileEntityBlockTrafficSign)
            {
                ((TileEntityBlockTrafficSign)tile).setSignType(message.signType);
                ((TileEntityBlockTrafficSign)tile).setSignShape(message.signShape);
            }
            return null;
        }
    }
}
