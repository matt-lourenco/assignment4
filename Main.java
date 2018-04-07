/****************************************************************************
 *
 * Created by: Matthew lourenco
 * Created on: Apr 2018
 * This program is a stub program that tests the Triangle class
 *
 ****************************************************************************/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		//Stub program to test the triangle class
		
		BufferedReader reader = new BufferedReader(new InputStreamReader
				(System.in));
		String[] names = {"Side A", "Side B", "Side C",
				"Angle A", "Angle B", "Angle C"};
		double[] inputs = new double[6];
		String input;
		int numberOfInputs = 0;
		
		for (int data = 0; data<names.length && numberOfInputs < 3; data++) {
			System.out.println("Would you like to input " + names[data] + "? (y/n)");
			if (reader.readLine().toLowerCase().equals("y")) {
				numberOfInputs++;
				System.out.println("Input " + names[data] + ": ");
				input = reader.readLine();
				inputs[data] = data < 3 ? Double.parseDouble(input) :
								Double.parseDouble(input) * Math.PI / 180;
			}
		}
		
		Triangle triangle = new Triangle(inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]);
		System.out.println(triangle.getData());
	}
}