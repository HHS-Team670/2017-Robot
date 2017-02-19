package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensor extends Subsystem {

	private double oneInchVoltage = 0.281982393;
	private double sixInchVoltage = 0.28076169;
	private double twelveInchVoltage = 0.295410126;
	private double eighteenInchVoltage = 0.414748046;
	private double twentyfourInchVoltage = 0.52490229;
	private AnalogInput analog;
	private double voltageProportionInches = 44.7854264;
	private double cmToInches = 2.54;

	
	public DistanceSensor()
	{
		analog = new AnalogInput(RobotMap.UltrasonicAI);
	}

	public Double getDistanceInches()
	{
		double distanceInches = calculateDistanceInches(getVoltage());
		distanceInches = (int)(distanceInches * 1000) / 1000;
		return distanceInches;
	}

	public double getVoltage()
	{
		return analog.getVoltage();
	}

	public Double getDistanceCM()
	{
		return getDistanceInches()*cmToInches;

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
	
	//Logarithmic Regression
	private double calculateDistanceInches(double voltage)
	{
		double A = 40.48446923;
		double B = 25.39389626;
		double X = voltage;
		double yIntercept = 2;
		double equation = (A + B * Math.log(X)) /* - yIntercept*/;
		return equation;
	}
	
}

