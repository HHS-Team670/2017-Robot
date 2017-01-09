package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {

	//Motor controllers
	public CANTalon leftTalon1;
	public CANTalon leftTalon2;
	public CANTalon rightTalon1;
	public CANTalon rightTalon2;
	//public CANTalon omniWheel;
	
	//Wheel/PID Variables
	public static final double radiusInInches = 10;
	public static final double diameterInInches = radiusInInches * 2;
	public static final double circumferenceInInches = diameterInInches * Math.PI;
	//public static final double inchesPerTick = circumferenceInInches / 360;

	public DriveBase() {
		leftTalon1 = new CANTalon(RobotMap.leftMotor1);
		leftTalon2 = new CANTalon(RobotMap.leftMotor2);
		rightTalon1 = new CANTalon(RobotMap.rightMotor1);
		rightTalon2 = new CANTalon(RobotMap.rightMotor2);
	//	omniWheel = new CANTalon(RobotMap.omniWheel);

		leftTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftTalon2.set(RobotMap.leftMotor1);
		rightTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightTalon2.set(RobotMap.rightMotor1);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void drive(double left, double right, double omni) {
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		leftTalon1.set(left);
		rightTalon1.set(right);
	//	omniWheel.set(omni);
	}
	
	public void tankDrive(double left, double right) 
	{
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		leftTalon1.set(left);
		rightTalon1.set(right);
	}
	
	public void omniWheelDrive(double omni)
	{
	//	omniWheel.set(omni);
	}

	public void resetRightEncoder() {
		rightTalon1.setEncPosition(0);
	}

	public void resetLeftEncoder() {
		leftTalon1.setEncPosition(0);
	}
	
	public void resetOmniEncoder() {
	//	omniWheel.setEncPosition(0);
	}

	public void posDriveLeft(double left) 
	{
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.reverseSensor(true);

		double p = .8;
		double i = .001;
		double d = 0;

		leftTalon1.setPID(p, i, d);

		leftTalon1.set(2520 * left * 0.5);
	}

	public void posDriveRight(double right) 
	{
										
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.reverseSensor(true);

		double p = .8;
		double i = .001;
		double d = 0;

		rightTalon1.setPID(p, i, d);
		
		rightTalon1.set(2520 * right * 0.5);
	}

	public void omniDriveDistance(double inches)
	{
		
	}
	
	public void tankDriveDistance(double inches) 
	{
		double numOfRotations = inches/circumferenceInInches;
		
		double p = .8;
		double i = .001;
		double d = 0;
		
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		rightTalon1.setPID(p, i, d); //Set the PID constants (p, i, d)
		rightTalon1.enableControl(); //Enable PID control on the talon
		
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		leftTalon1.setPID(p, i, d); //Set the PID constants (p, i, d)
		leftTalon1.enableControl(); //Enable PID control on the talon
		
		leftTalon1.set(5000);
		rightTalon1.set(5000);
	}

	public void pivot(double degrees) 
	{
		
	}
}