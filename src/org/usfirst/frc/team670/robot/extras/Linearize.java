package org.usfirst.frc.team670.robot.extras;

public class Linearize 
{
	/*
	 * Returns a positive cubic function output for the joystick input
	 * @param Joystick value
	 * @return New cubic Joystick value
	 */
	public static double getSwayOutput(double input)
	{
		double modi = 0.75;
		return modi*(Math.pow(input,3))+(1-modi)*input;
	}
}
