/****************************************************************************
 *
 * Created by: Matthew lourenco
 * Created on: Apr 2018
 * This program is a blueprint for a triangle
 *
 ****************************************************************************/

public class Triangle {
	// This class represents a triangle
	
	@SuppressWarnings("serial")
	public class InvalidValueException extends Exception {
	    public InvalidValueException(String cause) {
	        super(cause);
	    }
	}
	
	private Line base = new Line(0);
	private Line lineB = new Line(0);
	private Line lineC = new Line(0);
	
	private class Line {
		// This class represents a straight line
		
		private double length = 0;
		private double oppositeAngle = 0;
		
		private Line(double length) {
			// Constructor for line using length
			this.length = length;
		}
		
		public double getLength() { return length; } // Getter
		
		public double getDegrees() {
			// Get the size in degrees
			return oppositeAngle * 180 / Math.PI;
		}
		
		private boolean completePair() {
			//Checks if both length and angle are set
			return (length > 0 && oppositeAngle > 0 &&
					oppositeAngle < Math.PI);
		}
	}
	
	public Triangle() throws InvalidValueException {
		// Default constructor
		this(3, 3, 3, 0, 0, 0);
	}
	
	public Triangle(double length1, double length2, double length3,
					double angle1, double angle2, double angle3)
					throws InvalidValueException {
		// Constructor
		base.length = length1;
		lineB.length = length2;
		lineC.length = length3;
		base.oppositeAngle = angle1;
		lineB.oppositeAngle = angle2;
		lineC.oppositeAngle = angle3;
		
		isTriangleValid();
		
		Line sinePair = new Line(0); //Pair of length and angle for sine law
		Line[] lines = {base, lineB, lineC};
		for (Line line: lines) {
			if (line.completePair()) {
				sinePair = line;
				break;
			}
		}
		
		if (getValidLengths() == 3) {
			// Only lengths have been inputted
			
			// Find each angle
			base.oppositeAngle = cosLawAngle(base.length, lineB.length,
												lineC.length);
			lineB.oppositeAngle = sinLawAngle(base.length, base.oppositeAngle,
												lineB.length);
			lineC.oppositeAngle = Math.PI - base.oppositeAngle
								- lineB.oppositeAngle;
			
		} else if (getValidLengths() == 2) {
			// Two lengths and one angle have been inputted
			
			// complete the sine pair in case one was not already found
			if (sinePair.length == 0) {
				if (validAngle(base.oppositeAngle)) {
					base.length = cosLawLength(lineB.length,
											lineC.length,
											base.oppositeAngle);
					sinePair = base;
					
				} else if (validAngle(lineB.oppositeAngle)) {
					lineB.length = cosLawLength(base.length,
												lineC.length,
												lineB.oppositeAngle);
					sinePair = lineB;
					
				} else if (validAngle(lineC.oppositeAngle)) {
					lineC.length = cosLawLength(lineB.length,
												base.length,
												lineC.oppositeAngle);
					sinePair = lineC;
					
				}
			}
			
			// Find the second angle using sine law
			if (validLength(base.length) && !validAngle(base.oppositeAngle)) {
				base.oppositeAngle = sinLawAngle(sinePair.length,
						sinePair.oppositeAngle, base.length);
			} else if (validLength(lineB.length) && !validAngle(lineB.oppositeAngle)) {
				lineB.oppositeAngle = sinLawAngle(sinePair.length,
						sinePair.oppositeAngle, lineB.length);
			} else if (validLength(lineC.length) && !validAngle(lineC.oppositeAngle)) {
				lineC.oppositeAngle = sinLawAngle(sinePair.length,
						sinePair.oppositeAngle, lineC.length);
			}
			
			// Find the final angle using the sum of the angles
			if (!validAngle(base.oppositeAngle)) {
				base.oppositeAngle = Math.PI - lineB.oppositeAngle
						- lineC.oppositeAngle;
			} else if (!validAngle(lineB.oppositeAngle)) {
				lineB.oppositeAngle = Math.PI - base.oppositeAngle
						- lineC.oppositeAngle;
			} else if (!validAngle(lineC.oppositeAngle)) {
				lineC.oppositeAngle = Math.PI - lineB.oppositeAngle
						- base.oppositeAngle;
			}
			
			// Find the final length using sine law
			if (!validLength(base.length)) {
				base.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, base.oppositeAngle);
			} else if (!validLength(lineB.length)) {
				lineB.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, lineB.oppositeAngle);
			} else if (!validLength(lineC.length)) {
				lineC.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, lineC.oppositeAngle);
			}
			
		} else if (getValidLengths() == 1) {
			// One length and two angles have been inputted
			
			// Find the final angle using the sum of the angles
			if (!validAngle(base.oppositeAngle)) {
				base.oppositeAngle = Math.PI - lineB.oppositeAngle
						- lineC.oppositeAngle;
			} else if (!validAngle(lineB.oppositeAngle)) {
				lineB.oppositeAngle = Math.PI - base.oppositeAngle
						- lineC.oppositeAngle;
			} else if (!validAngle(lineC.oppositeAngle)) {
				lineC.oppositeAngle = Math.PI - lineB.oppositeAngle
						- base.oppositeAngle;
			}
			
			if (sinePair.length == 0) {
				// Find new sine pair in case one was not already found
				for (Line line: lines) {
					if (line.completePair()) {
						sinePair = line;
						break;
					}
				}
			}
			
			// Find the final two lengths using sine law
			if (!validLength(base.length)) {
				base.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, base.oppositeAngle);
			}
			if (!validLength(lineB.length)) {
				lineB.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, lineB.oppositeAngle);
			}
			if (!validLength(lineC.length)) {
				lineC.length = sinLawLength(sinePair.length,
						sinePair.oppositeAngle, lineC.oppositeAngle);
			}
		}
	}
	
	private double custRound(double value) {
		return Math.round(value * 1000d) / 1000d;
	}
	
	public Line getBase() { return base; } // Getter
	
	public Line getLineB() { return lineB; } // Getter
	
	public Line getLineC() { return lineC; } // Getter
	
	public String getName() {
		// Determines the name of the triangle based on side and angle lengths
		String name = "";
		double length1 = custRound(base.length);
		double length2 = custRound(lineB.length);
		double length3 = custRound(lineC.length);
		double angle1 = custRound(base.oppositeAngle);
		double angle2 = custRound(lineB.oppositeAngle);
		double angle3 = custRound(lineC.oppositeAngle);
		
		if (length1 == length2 && length1 == length3) {
			name = "Equilateral ";
		} else {
			if (angle1 == custRound(Math.PI / 2) ||
				angle2 == custRound(Math.PI / 2) ||
				angle3 == custRound(Math.PI / 2)) {
				name = "Right angle ";
			}
			if (length1 == length2 ||
						length1 == length3 ||
						length2 == length3) {
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
		return custRound(base.length + lineB.length + lineC.length);
	}
	
	public double getSemiperimeter() {
		// Getter
		return custRound(getPerimeter() / 2);
	}
	
	public double getArea() {
		// Getter
		double area = Math.sqrt(getSemiperimeter() * 
				(getSemiperimeter() - base.length) * 
				(getSemiperimeter() - lineB.length) * 
				(getSemiperimeter() - lineC.length));
		return custRound(area);
	}
	
	public double getHeight() {
		// Getter
		return custRound(getArea() * 2 / base.length);
	}
	
	public Circle getInCircle() throws Circle.InvalidValueException {
		// Getter
		Circle inscribedCircle = new Circle(getArea() / getSemiperimeter());
		return inscribedCircle;
	}
	
	public Circle getCircumcircle() throws Circle.InvalidValueException {
		// Getter
		double radius = base.length * lineB.length * lineC.length
				/ 4 / getArea();
		return new Circle(radius);
	}
	
	private boolean validLength(double length) {
		// Check if length is valid
		return length > 0;
	}
	
	private boolean validAngle(double angle) {
		// Check if angle is valid
		return angle > 0 && angle < Math.PI;
	}
	
	private int getValidLengths() {
		// Checks how many lengths of the triangle are valid
		double[] lengths = {base.length, lineB.length, lineC.length};
		int validLengths = 0;
		for (double length: lengths) {
			if (validLength(length)) {
				validLengths++;
			}
		}
		return validLengths;
	}
	
	private int getValidAngles() {
		// Checks how many values of the triangle are valid
		double[] angles = {base.oppositeAngle,
							lineB.oppositeAngle,
							lineC.oppositeAngle};
		int validAngles = 0;
		for (double angle: angles) {
			if (validAngle(angle)) {
				validAngles++;
			}
		}
		return validAngles;
	}
	
	protected void isTriangleValid() throws InvalidValueException {
		
		int lengths = getValidLengths();
		int angles = getValidAngles();
		
		if (lengths + angles != 3) {
			// Less than three values were inputted
			throw new InvalidValueException("Invalid number of valid inputs");
		}
		
		if (base.oppositeAngle + lineB.oppositeAngle +
				lineC.oppositeAngle > Math.PI) {
			// Angles add up to greater than PI
			throw new InvalidValueException("Angles add up to greater than PI");
		}
		
		if (lengths == 0 && angles == 3) {
			// Only three angles were inputted
			throw new InvalidValueException("Only angles were inputted");
		}
	}
	
	private double sinLawLength(double pairLength, double pairAngle,
								double unpairedAngle) {
		// This method uses the sine law to determine a length
		return pairLength * Math.sin(unpairedAngle) / Math.sin(pairAngle);
	}
	
	private double sinLawAngle(double pairLength, double pairAngle,
								double unpairedLength) {
		// This method uses the sine law to determine an angle
		return Math.asin(Math.sin(pairAngle) * unpairedLength / pairLength);
	}
	
	private double cosLawLength(double length1, double length2,
								double angle) {
		// This method uses the cosine law to determine a length
		return Math.sqrt(Math.pow(length1, 2) + Math.pow(length2, 2) -
				(2 * length1 * length2 * Math.cos(angle)));
	}
	
	private double cosLawAngle(double oppositeSide, double length1,
								double length2) {
		// This method uses the cosine law to determine an angle
		return Math.acos((Math.pow(oppositeSide, 2) - Math.pow(length1, 2) -
				Math.pow(length2, 2)) / (-2 * length1 * length2));
	}
	
	public String getData() throws Exception {
		// Returns all data of the triangle
		return "Name: " + getName() + "\n" +
				"Side A length: " + custRound(base.length) + "\n" +
				"Angle A: " + custRound(base.getDegrees()) + " degrees\n" +
				"Side B length: " + custRound(lineB.length) + "\n" +
				"Angle B: " + custRound(lineB.getDegrees()) + " degrees\n" +
				"Side C length: " + custRound(lineC.length) + "\n" +
				"Angle C: " + custRound(lineC.getDegrees()) + " degrees\n" +
				"Height: " + getHeight() + "\n" +
				"Perimeter: " + getPerimeter() + "\n" +
				"Semi-perimeter: " + getSemiperimeter() + "\n" +
				"Area: " + getArea() + "\n" +
				"\nLargest Inscribed circle:\n" + getInCircle().getData() + "\n" +
				"\nCircumcircle:\n" + getCircumcircle().getData();
	}
}