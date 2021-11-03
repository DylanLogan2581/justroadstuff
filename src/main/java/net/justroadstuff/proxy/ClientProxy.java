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

package net.justroadstuff.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.justroadstuff.client.render.RenderAsphaltArrows;
import net.justroadstuff.client.render.RenderAsphaltCorner;
import net.justroadstuff.client.render.RenderAsphaltLines;
import net.justroadstuff.client.render.RenderCone;
import net.justroadstuff.client.render.RenderReflector;
import net.justroadstuff.client.render.RenderSlabAsphaltCorner;
import net.justroadstuff.client.render.RenderSlabAsphaltLine;
import net.justroadstuff.client.render.RenderTrafficSign;

public class ClientProxy extends CommonProxy
{

    public static int renderAsphaltLinesId, renderAsphaltCornerId, renderAsphaltArrowsId, renderSlabAsphaltLineId, renderConeId, renderSignPostId, renderSlabAsphaltCornerId, renderReflectorId;

    @Override
    public void registerRender()
    {
        renderConeId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderConeId, new RenderCone());
        renderAsphaltLinesId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderAsphaltLinesId, new RenderAsphaltLines());
        renderAsphaltCornerId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderAsphaltCornerId, new RenderAsphaltCorner());
        renderAsphaltArrowsId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderAsphaltArrowsId, new RenderAsphaltArrows());
        renderSlabAsphaltLineId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderSlabAsphaltLineId, new RenderSlabAsphaltLine());
        renderSlabAsphaltCornerId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderSlabAsphaltCornerId, new RenderSlabAsphaltCorner());
        renderSignPostId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderSignPostId, new RenderTrafficSign());
        renderReflectorId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderReflectorId, new RenderReflector());
    }
}
