package geometries;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static primitives.Util.*;

import primitives.*;

public class Polygon extends Geometry {
	List<Point3D> _points;
	Plane _plane;
	// ***************** Constructors ********************** //

	/**
	 * Constructs a polygon from set of points - polygon's vertices. NB: the points
	 * must be in the same plane
	 *
	 * @param points vertices
	 * @throws IllegalArgumentException if less than 3 points or points are not in
	 *                                  the same plane
	 */
	public Polygon(Point3D... points) {
		if (points.length < 3)
			throw new IllegalArgumentException("Polygon must have at least 3 vertices");
		Point3D p1 = points[0];
		Point3D p2 = points[1];
		Point3D p3 = points[2];
		_plane = new Plane(p1, p2, p3);
		Vector n = _plane.getNormal();
		for (int i = 3; i < points.length; ++i)
			if (!isZero(p1.sub(points[i]).dotProduct(n)))
				throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
		_points = Arrays.asList(points);
	}
	/**
     * Constructs a polygon from set of points - polygon's vertices and a color. NB: the points
     * must be in the same plane
     *
     * @param emission the color of the polygon
     * @param points   vertices
     * @throws IllegalArgumentException if less than 3 points or points are not in
     *                                  the same plane
     */
    public Polygon(Color emission, Point3D... points) {
        if (points.length < 3)
            throw new IllegalArgumentException("Polygon must have at least 3 vertices");
        Point3D p1 = points[0];
        Point3D p2 = points[1];
        Point3D p3 = points[2];
        _plane = new Plane(emission, p1, p2, p3);
        Vector n = _plane.getNormal();
        for (int i = 3; i < points.length; ++i)
            if (!isZero(p1.sub(points[i]).dotProduct(n)))
                throw new IllegalArgumentException("Polygon's vertices must resize in the same plane");
        _points = Arrays.asList(points);
        setEmission(emission);
    }

	// ***************** operations ********************** //
	/**
	 * Gets the normal of the polygon to the point
	 *
	 * @return the normal of the plane of the polygon
	 */
	public Vector getNormal(Point3D p) {
		return _plane.getNormal(p);
	}

	/**
     * finds intersections of the ray with the polygon
     *
     * @param ray
     * @return intersection point
     */
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = this._plane.findIntersections(ray);
        if (intersections == null) //  there are no intersections with the plane
                    return null;

        Point3D p0 = new Point3D(ray.getP());
        int size = this._points.size();
        Vector[] v = new Vector[size];
        Vector[] n = new Vector[size];
        double[] x = new double[size];
        for (int i = 0; i < size; ++i)
            v[i] = _points.get(i).sub(p0);
        
        for (int i = 0; i < size; ++i)
            n[i] = v[i].crossProduct(v[(i < size - 1) ? i + 1 : 0]).normalize();

         Vector u = intersections.get(0).getPoint().sub(p0);
       
        for (int i = 0; i < size; ++i)
            if ((x[i] = alignZero(u.dotProduct(n[i]))) == 0)
                return null;
        double t = x[0];
        for (int i = 1; i < size; ++i)
           
            if ((t < 0 && x[i] > 0) || ( t> 0 && x[i] < 0))
                return null;

        return intersections;
    }

	}
