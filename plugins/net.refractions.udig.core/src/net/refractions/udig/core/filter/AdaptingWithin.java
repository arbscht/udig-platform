/*
 *    uDig - User Friendly Desktop Internet GIS client
 *    http://udig.refractions.net
 *    (C) 2012, Refractions Research Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Refractions BSD
 * License v1.0 (http://udig.refractions.net/files/bsd3-v10.html).
 */
package net.refractions.udig.core.filter;

import org.opengis.filter.expression.Expression;
import org.opengis.filter.spatial.Within;

/**
 * AdaptingFilter that implements Within interface.
 * 
 * @author Jody
 * @since 1.1.0
 */
class AdaptingWithin extends AdaptingFilter<Within> implements Within {

    AdaptingWithin( Within filter ) {
        super(filter);
    }

    public Expression getExpression1() {
        return wrapped.getExpression1();
    }

    public Expression getExpression2() {
        return wrapped.getExpression1();
    }
    public MatchAction getMatchAction() {
        return wrapped.getMatchAction();
    }
}
