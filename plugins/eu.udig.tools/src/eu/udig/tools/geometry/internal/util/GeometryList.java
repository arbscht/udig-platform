/* Spatial Operations & Editing Tools for uDig
 * 
 * Axios Engineering under a funding contract with: 
 *      Diputación Foral de Gipuzkoa, Ordenación Territorial 
 *
 *      http://b5m.gipuzkoa.net
 *      http://www.axios.es 
 *
 * (C) 2006, Diputación Foral de Gipuzkoa, Ordenación Territorial (DFG-OT). 
 * DFG-OT agrees to licence under Lesser General Public License (LGPL).
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Axios BSD
 * License v1.0 (http://udig.refractions.net/files/asd3-v10.html).
 */
package eu.udig.tools.geometry.internal.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.vividsolutions.jts.geom.Geometry;

/**
 * 
 * List of Geometries
 * <p>
 * It is a workaround to allow compare geometries by its coordinates 
 * </p>
 * @author Mauricio Pazos (www.axios.es)
 * @author Aritz Davila (www.axios.es)
 * @since 1.3.0
 * @param <T>
 */
public final class GeometryList< T extends Geometry> extends LinkedList<T> implements List<T>, Deque<T>, Cloneable, Serializable{

    private static final long serialVersionUID = 3477623090320403970L;
    

    public GeometryList() {
        super();
    }
    
    public GeometryList( Collection<T> values ) {
        super(values);
    }

    /**
     *  Compares the geometries using {@link Geometry#equalsExact(Geometry)}
     */
    @Override
    public boolean contains(Object o) {
        
        T requested = (T) o;
        for( T g: this ) {
            
            T p = (T)g;
            if(p.equalsExact(requested)){ 
                return true;
            }
        }
        return false;
    }
    
}
