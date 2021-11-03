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

package net.justroadstuff.common;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.justroadstuff.client.gui.JustRoadStuffGuiHandler;
import net.justroadstuff.common.init.JustRoadStuffBlocks;
import net.justroadstuff.common.network.PacketSignType;
import net.justroadstuff.common.tiles.TileEntityBlockTrafficSign;
import net.justroadstuff.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = JustRoadStuff.MODID, name = "Road Stuff", version = Reference.version)

public class JustRoadStuff
{
    @Instance("justroadstuff")
    public static JustRoadStuff instance;

    public static final String MODID = "justroadstuff";

    public static Logger logger;

    public static SimpleNetworkWrapper network;

    @SidedProxy(clientSide = "net.justroadstuff.proxy.ClientProxy", serverSide = "net.justroadstuff.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs JustRoadStuffCreativeTabs = new CreativeTabs("JustRoadStuff")
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(JustRoadStuffBlocks.blockCone);
        }

        @SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
            return 0;
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        JustRoadStuffBlocks.initBlocks();

        network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        network.registerMessage(PacketSignType.Handler.class, PacketSignType.class, 0, Side.SERVER);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerTileEntity(TileEntityBlockTrafficSign.class, JustRoadStuff.MODID + ":entityBlockSign");

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new JustRoadStuffGuiHandler());

        proxy.registerRender();

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {

    }

    @EventHandler
    public void onServerStart(FMLServerStartingEvent event)
    {

    }

}
