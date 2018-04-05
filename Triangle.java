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
		
		double size;
		
		public Angle() throws InvalidValueException {
			// Default constructor
			this(Math.PI);
		}
		
		public Angle(double size) throws InvalidValueException {
			// Constructor
			this.setSize(size);
		}
		
		public double getSize() { return size; }
		
		public void setSize(double newSize) throws InvalidValueException {
			if (newSize >= 0 && newSize <= 2*Math.PI) {
				size = newSize;
			} else {
				throw new InvalidValueException("Angle out of valid range");
			}
		}
	}
	
	private class Line {
		// This class represents a straight line
		
		double length;
		private Angle oppositeAngle;
	}
}