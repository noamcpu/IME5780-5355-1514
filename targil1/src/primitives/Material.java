
package primitives;

/**
 * class represents a material of a geometry
 */
public class Material {
	private double _kD;
	private double _kS;
	private int _nShininess;
	private double _kR;
	private double _kT;

	// ***************** Constructors ********************** //

	/**
	 * creates a material for a geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 */
	public Material(double kd, double ks, int shininess) {
		_kD = kd;
		_kS = ks;
		_nShininess = shininess;
		_kR = 0;
		_kT = 0;
	}

	/**
	 * creates a material for a geometry
	 *
	 * @param kd        - diffusion attenuation coefficient
	 * @param ks        - specular attenuation coefficient
	 * @param shininess - shininess power
	 * @param kr        - reflection coefficient
	 * @param kt        - transparency coefficient
	 */
	public Material(double kd, double ks, int shininess, double kr, double kt) {
		this(kd, ks, shininess);
		_kR = kr;
		_kT = kt;
	}
	// ***************** Getters/Setters ********************** //

	/**
	 * getter of diffusion attenuation coefficient
	 *
	 * @return diffusion attenuation coefficient
	 */
	public double getKd() {
		return _kD;
	}

	/**
	 * getter of specular attenuation coefficient
	 *
	 * @return specular attenuation coefficient
	 */
	public double getKs() {
		return _kS;
	}

	/**
	 * getter of shininess power
	 *
	 * @return shininess power
	 */
	public int getShininess() {
		return _nShininess;
	}

	/**
	 * getter of reflection coefficient
	 *
	 * @return reflection coefficient
	 */
	public double getKr() {
		return _kR;
	}

	/**
	 * getter of transparency coefficient
	 *
	 * @return transparency coefficient
	 */
	public double getKt() {
		return _kT;
	}
}
