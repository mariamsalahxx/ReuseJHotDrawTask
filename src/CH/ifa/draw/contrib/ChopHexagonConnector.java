/*
 * @(#)ChopDiamondConnector.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	� by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */

package CH.ifa.draw.contrib;

import java.awt.*;
import CH.ifa.draw.framework.*;
import CH.ifa.draw.standard.ChopBoxConnector;
import CH.ifa.draw.util.Geom;

/**
 * A ChopDiamondConnector locates connection points by choping the
 * connection between the centers of the two figures at the edge of
 * a diamond figure.
 *
 * @see Connector
 *
 * @author Mariam Salah
 * @version <$CURRENT_VERSION$>
 */
public class ChopHexagonConnector extends ChopBoxConnector {
    /**
     * Serialization support.
     * Needs to be fixed.
     */
    private static final long serialVersionUID = -1461450322512395462L;

    public ChopHexagonConnector() {
        // only used for Storable implementation
    }

    public ChopHexagonConnector(Figure owner) {
        super(owner);
    }

    protected Point chop(Figure target, Point from) {
        Rectangle r = target.displayBox();
        Point center = new Point(r.x + r.width / 2, r.y + r.height / 2);

        Point[] vertices = new Point[6];
        vertices[0] = new Point(r.x + r.width / 4, r.y);
        vertices[1] = new Point(r.x + 3 * r.width / 4, r.y);
        vertices[2] = new Point(r.x + r.width, r.y + r.height / 2);
        vertices[3] = new Point(r.x + 3 * r.width / 4, r.y + r.height);
        vertices[4] = new Point(r.x + r.width / 4, r.y + r.height);
        vertices[5] = new Point(r.x, r.y + r.height / 2);

        if (r.contains(from)) {
            return closestVertex(center, from, vertices);
        }

        Point intersection = null;
        for (int i = 0; i < vertices.length; i++) {
            Point start = vertices[i];
            Point end = vertices[(i + 1) % vertices.length];
            intersection = intersect(from, center, start, end);
            if (intersection != null) {
                break;
            }
        }

        if (intersection == null) {
            intersection = closestVertex(center, from, vertices);
        }

        return intersection;
    }
    private Point intersect(Point p1, Point p2, Point p3, Point p4) {
        // Line 1
        double a1 = p2.y - p1.y;
        double b1 = p1.x - p2.x;
        double c1 = a1 * p1.x + b1 * p1.y;

        // Line 2
        double a2 = p4.y - p3.y;
        double b2 = p3.x - p4.x;
        double c2 = a2 * p3.x + b2 * p3.y;

        // Intersection calculation
        double determinant = a1 * b2 - a2 * b1;
        if (determinant == 0) {
            // The lines are parallel
            return null;
        } else {
            // The intersection point
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            // Check if the intersection is within the line segments
            if (isBetween(x, p1.x, p2.x) && isBetween(x, p3.x, p4.x) &&
                    isBetween(y, p1.y, p2.y) && isBetween(y, p3.y, p4.y)) {
                return new Point((int) x, (int) y);
            }
            return null;
        }
    }

    private boolean isBetween(double value, double bound1, double bound2) {
        return Math.min(bound1, bound2) <= value && value <= Math.max(bound1, bound2);
    }
    private Point closestVertex(Point center, Point from, Point[] vertices) {
        Point closest = vertices[0];
        double minDistance = center.distance(vertices[0]);
        for (int i = 1; i < vertices.length; i++) {
            double distance = center.distance(vertices[i]);
            if (distance < minDistance) {
                closest = vertices[i];
                minDistance = distance;
            }
        }
        return closest;
    }
}
