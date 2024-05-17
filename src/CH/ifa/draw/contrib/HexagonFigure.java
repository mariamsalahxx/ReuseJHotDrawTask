/*
 * @(#)DiamondFigure.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	� by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.contrib;

import CH.ifa.draw.framework.*;
import CH.ifa.draw.util.*;
import CH.ifa.draw.standard.*;
import CH.ifa.draw.figures.*;
import java.awt.*;
import java.util.*;
import java.io.IOException;

/**
 * A hexagon with vertices at the midpoints of its enclosing rectangle
 *
 * @author Mariam Salah
 * @version <$CURRENT_VERSION$>
 */
public  class HexagonFigure extends RectangleFigure {

    public HexagonFigure() {
        super(new Point(0,0), new Point(0,0));
    }

    public HexagonFigure(Point origin, Point corner) {
        super(origin,corner);
    }

    /**
     * @return the polygon describing the hexagon
     **/
    protected Polygon getPolygon() {
        Rectangle r = displayBox();
        Polygon p = new Polygon();
        p.addPoint(r.x + r.width / 4, r.y); // Mid-top left
        p.addPoint(r.x + 3 * r.width / 4, r.y); // Mid-top right
        p.addPoint(r.x + r.width, r.y + r.height / 2); // Right middle
        p.addPoint(r.x + 3 * r.width / 4, r.y + r.height); // Mid-bottom right
        p.addPoint(r.x + r.width / 4, r.y + r.height); // Mid-bottom left
        p.addPoint(r.x, r.y + r.height / 2); // Left middle
        return p;
    }

    public void draw(Graphics g) {
        Polygon p = getPolygon();
        g.setColor(getFillColor());
        g.fillPolygon(p);
        g.setColor(getFrameColor());
        g.drawPolygon(p);
    }

    public Insets connectionInsets() {
        Rectangle r = displayBox();
        return new Insets(r.height/2, r.width/2, r.height/2, r.width/2);
    }

    public boolean containsPoint(int x, int y) {
        return getPolygon().contains(x, y);
    }

    public Point chop(Point p) {
        return PolygonFigure.chop(getPolygon(), p);
    }

    public Connector connectorAt(int x, int y) {
        return new ChopHexagonConnector(this);
    }
}
