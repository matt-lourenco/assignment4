/****************************************************************************
 *
 * Created by: Matthew lourenco
 * Created on: Apr 2018
 * This program is a blueprint for a triangle
 *
 ****************************************************************************/

public class Triangle {
	// This class represents a triangle
	
	public class InvalidValueException extends Exception {
	    public InvalidValueException(String cause) {
	        super(cause);
	    }
	}
	
	private Line base;
	private Line lineA;
	private Line lineB;
	
	private class Angle {
		// This class represents an angle
		
		private double size;
		
		private Angle() throws InvalidValueException {
			// Default constructor
			this(Math.PI);
		}
		
		private Angle(double size) throws InvalidValueException {
			// Constructor
			this.setSize(size);
		}
		
		private double getSize() { return size; }
		
		private void setSize(double newSize) throws InvalidValueException {
			if (newSize >= 0 && newSize <= 2*Math.PI) {
				size = newSize;
			} else {
				throw new InvalidValueException("Angle out of valid range");
			}
		}
	}
	
	private class Line {
		// This class represents a straight line
		
		public double length;
		private Angle oppositeAngle;
		
		private Line(double length) {
			// Constructor for line using length
			this.length = length;
		}
		
		private Line(Angle angle) {
			// Constructor for line using Angle
			this.oppositeAngle = angle;
		}
		
		public double getOppAngle() {
			// Getter
			return oppositeAngle.getSize();
		}
		
		private void setOppAngle(double newAngle) throws InvalidValueException {
			// Setter
			oppositeAngle.setSize(newAngle);
		}
	}
	
	public Triangle(double length1, double length2, double length3) throws InvalidValueException {
		// Constructor including three lines
		base = new Line(length1);
		lineA = new Line(length2);
		lineB = new Line(length3);
		base.setOppAngle(this.cosLawAngle(base.length, lineA.length, lineB.length));
		lineA.setOppAngle(this.sinLawAngle(base.length, base.getOppAngle(), lineA.length));
		lineB.setOppAngle(Math.PI - base.getOppAngle() - lineB.getOppAngle());
		this.isTriangleValid();
		}
	
	public Triangle(double length1, double length2, Angle angle) {
		// Constructor including two lines and an angle
	}
	
	public Triangle(double length, Angle angle1, Angle angle2) {
		// Constructor including one line and two angles
	}
	
	public Line getBase() { return base; } // Getter
	
	public Line getLineA() { return lineA; } // Getter
	
	public Line getLineB() { return lineB; } // Getter
	
	public String getName() {
		// Determines the name of the triangle based on side and angle lengths
		String name = "";
		if (base.length == lineA.length && base.length == lineB.length) {
			name = "Equilateral ";
		} else {
			if (base.getOppAngle() == Math.PI || lineA.getOppAngle() == Math.PI || lineB.getOppAngle() == Math.PI) {
				name = "Right angle ";
			} else if (base.length == lineA.length || base.length == lineB.length || lineA.length == lineB.length) {
				name += "Isosceles ";
			}
		}
		
		if (name.equals("")) {
			name = "Scalene triangle";
		} else {
			name += "triangle";
		}
		
		return name;
	}
	
	public double getPerimeter() {
		// Getter
		return base.length + lineA.length + lineB.length;
	}
	
	public double getSemiperimeter() {
		// Getter
		return this.getPerimeter() / 2;
	}
	
	public double getArea() {
		// Getter
		double area = Math.sqrt(this.getSemiperimeter() * 
				(this.getSemiperimeter() - base.length) * 
				(this.getSemiperimeter() - lineA.length) * 
				(this.getSemiperimeter() - lineB.length));
		return area;
	}
	
	public double getHeight() {
		// Getter
		return this.getArea() * 2 / base.length;
	}
	
	public Circle getInCircle() throws Circle.InvalidValueException {
		// Getter
		Circle inscribedCircle = new Circle(getArea() / this.getSemiperimeter());
		return inscribedCircle;
	}
	
	public Circle getCircumcircle() throws Circle.InvalidValueException {
		// Getter
		double radius = base.length * lineA.length * lineB.length / 4 / getArea();
		return new Circle(radius);
	}
	
	private boolean isInputValid(Angle input) {
		// Determine if an angle is valid
		return (input.getSize() > 0 && input.getSize() < Math.PI);
	}
	
	private boolean isInputValid(double input) {
		// Determine if a length is valid
		return input > 0;
	}
	
	private boolean isTriangleValid() {
		// Determine if entire triangle is valid
		return (isInputValid(base.length) && isInputValid(base.oppositeAngle) &&
				isInputValid(lineA.length) && isInputValid(lineA.oppositeAngle) &&
				isInputValid(lineB.length) && isInputValid(lineB.oppositeAngle));
	}
	
	private double sinLawLength(double pairLength, double pairAngle, double unpairedAngle) {
		// This method uses the sine law to determine a length
		return pairLength * Math.sin(unpairedAngle) / Math.sin(pairAngle);
	}
	
	private double sinLawAngle(double pairLength, double pairAngle, double unpairedLength) {
		// This method uses the sine law to determine an angle
		return Math.asin(Math.sin(pairAngle) * unpairedLength / pairLength);
	}
	
	private double cosLawLength(double length1, double length2, double angle) {
		// This method uses the cosine law to determine a length
		return Math.sqrt(Math.pow(length1, 2) + Math.pow(length2, 2) -
				(2 * length1 * length2 * Math.cos(angle)));
	}
	
	private double cosLawAngle(double oppositeSide, double length1, double length2) {
		// This method uses the cosine law to determine an angle
		return Math.acos((Math.pow(oppositeSide, 2) - Math.pow(length1, 2) -
				Math.pow(length2, 2)) / (-2 * length1 * length2));
	}
}