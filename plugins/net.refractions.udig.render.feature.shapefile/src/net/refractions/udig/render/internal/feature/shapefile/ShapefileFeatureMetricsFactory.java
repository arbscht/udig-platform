/*
 *    uDig - User Friendly Desktop Internet GIS client
 *    http://udig.refractions.net
 *    (C) 2004, Refractions Research Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Refractions BSD
 * License v1.0 (http://udig.refractions.net/files/bsd3-v10.html).
 *
 */
package net.refractions.udig.render.internal.feature.shapefile;

import net.refractions.udig.catalog.IGeoResource;
import net.refractions.udig.project.render.IRenderContext;
import net.refractions.udig.project.render.IRenderMetricsFactory;
import net.refractions.udig.project.render.IRenderer;

import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.view.DefaultView;

/**
 * The RenderMetricsFactory object for the BasicFeatureRenderer Extension
 *
 * @author Jesse Eichar
 * @version $Revision: 1.9 $
 */
public class ShapefileFeatureMetricsFactory implements IRenderMetricsFactory {

    /**
     * @see net.refractions.udig.project.render.IRenderMetricsFactory#createMetrics(net.refractions.udig.project.render.IRenderContext)
     */
    public ShapefileFeatureMetrics createMetrics(IRenderContext context) {
        return new ShapefileFeatureMetrics(context, this);
    }

    /**
     * @see net.refractions.udig.project.render.IRenderMetricsFactory#canRender(net.refractions.udig.project.render.IRenderContext)
     */
    @SuppressWarnings("unchecked")
    public boolean canRender(IRenderContext context) {
        try {
            IGeoResource geoResource = context.getGeoResource();
            if( geoResource.canResolve(ShapefileDataStore.class)){
                FeatureSource featureSource=geoResource.resolve(FeatureSource.class, null);
                
                boolean notAView = !(featureSource instanceof DefaultView);
                boolean isAShapefile = (featureSource.getDataStore() instanceof ShapefileDataStore);
                return notAView && featureSource!=null && isAShapefile; 
            }
        }
        catch( Throwable t ) {
        }
        return false;
    }

    /**
     * @see IRenderMetricsFactory#getRendererType()
     */
    public Class<? extends IRenderer> getRendererType() {
        return ShapefileFeatureRenderer.class;
    }

}
