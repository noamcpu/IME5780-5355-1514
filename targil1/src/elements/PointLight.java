package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class represents a point light in a scene
 */
public class PointLight extends Light implements LightSource {
	Point3D _position;
	double _kC, _kL, _kQ;
	// ***************** Constructors ********************** //

	/**
	 * constructs a point light
	 *
	 * @param color    - color of the light
	 * @param position - position of the light in the space
	 * @param kC
	 * @param kL
	 * @param kQ
	 */
	public PointLight(Color color, Point3D position, double kC, double kL, double kQ) {
		_intensity = color;
		this._position = position;
		this._kC = kC; // kc >= 1 - because the light reduces by the distance
		this._kL = kL; // linear attenuation reduction
		this._kQ = kQ; // quadratic attenuation reduction
	}

	// ***************** Getters/Setters ********************** //
	@Override
	public Vector getL(Point3D p) {
		return p.sub(_position).normalize();
	}
	@Override
	public Color getIntensity(Point3D p) {
		double d = _position.distance(p);
		double divisionFactor = _kC + _kL * d + _kQ * d * d;
			return getIntensity().reduce(divisionFactor);
	}

	public double getDistance(Point3D p) {
		return this._position.distance(p);
	}
}
