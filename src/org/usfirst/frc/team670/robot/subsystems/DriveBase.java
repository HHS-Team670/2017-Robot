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
	public CANTalon omniTalon;
	
	//Wheel/PID Variables
	public static final double radiusInInches = 3;
	public static final double diameterInInches = radiusInInches * 2;
	public static final double circumferenceInInches = diameterInInches * Math.PI;
	public static final double inchesPerTick = circumferenceInInches / 360;
	public static final double P = 0.8, I = 0.001, D = 0;
	//Pivot radius in inches
	public static final double pivotRadius = 16;
	
	//Drive only with omniwheel
	private static boolean isOmniDrive = false;

	public DriveBase() {
		leftTalon1 = new CANTalon(RobotMap.leftMotor1);
		leftTalon2 = new CANTalon(RobotMap.leftMotor2);
		rightTalon1 = new CANTalon(RobotMap.rightMotor1);
		rightTalon2 = new CANTalon(RobotMap.rightMotor2);
		omniTalon = new CANTalon(RobotMap.omniWheel);

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

		leftTalon1.set(-left);
		rightTalon1.set(right);
		omniTalon.set(omni);
	}
	
	public void tankDrive(double left, double right) 
	{
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		leftTalon1.set(-left);
		rightTalon1.set(right);
	}
	
	public void omniDrive(double omni)
	{
		omniTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		omniTalon.set(omni);
	}

	public void resetRightEncoder() {
		rightTalon1.setEncPosition(0);
	}

	public void resetLeftEncoder() {
		leftTalon1.setEncPosition(0);
	}
	
	public void resetOmniEncoder() {
		omniTalon.setEncPosition(0);
	}

	public void posDriveLeft(double left) 
	{
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.reverseSensor(true);

		leftTalon1.setPID(P,I,D);

		leftTalon1.set(-2520 * left * 0.5);
	}

	public void posDriveRight(double right) 
	{
										
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.reverseSensor(true);

		rightTalon1.setPID(P,I,D);
		
		rightTalon1.set(2520 * right * 0.5);
	}

	public void omniDriveDistance(double inches)
	{
		double numTicks = ((inches / inchesPerTick) / 360) * 2520;

		omniTalon.changeControlMode(CANTalon.TalonControlMode.Position);
		omniTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		omniTalon.setPID(P,I,D); //Set the PID constants (p, i, d)
		omniTalon.enableControl(); //Enable PID control on the talon
		omniTalon.set(numTicks);
	}
	
	public void tankDriveDistance(double inches) 
	{
		double numTicks = ((inches / inchesPerTick) / 360) * 2520;

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		rightTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)
		rightTalon1.enableControl(); //Enable PID control on the talon
		
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		leftTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)
		leftTalon1.enableControl(); //Enable PID control on the talon
		
		leftTalon1.set(-numTicks);
		rightTalon1.set(numTicks);
	}

	public void pivot(double degrees) 
	{
		double pivotCircumference = 2 * Math.PI * pivotRadius;
		double pivotArcLength = (degrees / 360) * pivotCircumference;
		double numTicks = ((pivotArcLength / inchesPerTick) / 360) * 2520;

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.setEncPosition(0);

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.setEncPosition(0);
	
		double p = P;
		double i = I;
		double d = D;

		leftTalon1.setPID(p, i, d);
		rightTalon1.setPID(p, i, d);

		leftTalon1.set(-numTicks);
		rightTalon1.set(numTicks);
	}
	
	public boolean isOmniDriving()
	{
		return isOmniDrive;
	}
	
	public void setOmniDrive(boolean drive)
	{
		isOmniDrive = drive;
	}
}