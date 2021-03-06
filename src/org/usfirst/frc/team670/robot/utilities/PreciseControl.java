package org.usfirst.frc.team670.robot.utilities;

public class PreciseControl 
{

	private double maxOutput = 0;
	
	/*
	 * @param Maximum possible value that can be returned
	 */
	public PreciseControl(double maxOutput)
	{
		this.maxOutput = maxOutput;
	}
	
	/*
	 * @param value pass in a value from the joystick
	 * @return value of the passed in value put through a cubic equation to get a "sway" control
	 * #Sensitivity things with slight acceleration
	 */
	public double getFFTOutput(double value)
	{
		double a = (value/maxOutput);
		return a*Math.pow(value,3) + (1-a)*value;
	}
	
	/*
	 * Square input with sign preservation
	 * #Exponential
	 */
	public double getSquare(double value)
	{
		return value * Math.abs(value);
	}
	
	/*
	 * Racing mode, if you want to use the robot like a race car
	 * #Rapidly increasing curve based on the biological carrying capacity curve
	 */
	public double getAccelerationCurve(double value)
	{
		double output = maxOutput*(0.5*value*(100-2)/100)/100;
		return output;		
	}
	
}