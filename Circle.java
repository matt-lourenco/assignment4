/****************************************************************************
 *
 * Created by: Matthew lourenco
 * Created on: Apr 2018
 * This program is a blueprint for a Circle
 *
 ****************************************************************************/

public class Circle {
	// This class represents a circle
	
	public class InvalidValueException extends Exception {
	    public InvalidValueException(String cause) {
	        super(cause);
	    }
	}
	
	private double radius = 1;
	
	public Circle(double radius) throws InvalidValueException {
		// Constructor for Circle class
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new InvalidValueException("Radius is zero or negative");
		}
	}
	
	private double custRound(double value) {
		return Math.round(value * 1000d) / 1000d;
	}
	
	public double getRadius() { return custRound(radius); } // Getter
	
	public double getCircumference() {
		//Calculates and returns the circumference
		return custRound(2 * radius * Math.PI);
	}
	
	public double getArea() {
		//Calculates and returns the area
		return custRound(Math.PI * Math.pow(radius, 2));
	}
	
	public String getData() {
		// Returns all data of the circle
		return "Radius: " + getRadius() + "\n" +
				"Circumference: " + getCircumference() + "\n" +
				"Area: " + getArea();
	}
}