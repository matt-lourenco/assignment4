/****************************************************************************
 *
 * Created by: Matthew lourenco
 * Created on: Apr 2018
 * This program is a blueprint for a Circle
 *
 ****************************************************************************/

public class Circle {
	// This class represents a circle
	
	double radius;
	
	public Circle(double radius) {
		// Constructor for Circle class
		this.radius = radius;
	}
	
	public double getRadius() { return radius; } // Getter
	
	public double getCircumfrence() {
		//Calculates and returns the circumfrence
		return 2 * radius * Math.PI;
	}
	
	public double getArea() {
		//Calculates and returns the area
		return Math.PI * Math.pow(radius, 2);
	}
}