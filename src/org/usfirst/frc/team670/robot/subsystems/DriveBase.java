package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team670.robot.enums.DriveState;

import com.ctre.*;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem {

	//Motor controllers
	public CANTalon leftTalon1;
	public CANTalon leftTalon2;
	public CANTalon rightTalon1;
	public CANTalon rightTalon2;
	public CANTalon omniTalon;

	//Converts ticks distance to a number the talon can read (It needs it multiplied for some reason)
	private final int talonConversion = 2520;

	//Wheel/PID Variables
	public static final double radiusInInches = 3;
	public static final double diameterInInches = radiusInInches * 2;
	public static final double circumferenceInInches = diameterInInches * Math.PI;
	public static final double inchesPerTick = circumferenceInInches / 360;
	public static final double P = 0.8, I = 0.05, D = 0;
	//0.001 at a time for I and D, 0.05 at a time for P.
	//Old Robot --> P:0.8, I:0.001, D = 0;
	//Pivot radius in inches
	public static final double pivotRadius = Math.sqrt(277);

	//Drive only with omniwheel
	//private static boolean isOmniDrive = false;
	private static DriveState current = DriveState.FOURWHEEL;
	private static final double speedMultiple = 1.2;
	
//	private Encoder encLeft = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
//	private Encoder encRight = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	
	
	public DriveBase() {
		leftTalon1 = new CANTalon(RobotMap.leftMotor1);
		leftTalon2 = new CANTalon(RobotMap.leftMotor2);
		rightTalon1 = new CANTalon(RobotMap.rightMotor1);
		rightTalon2 = new CANTalon(RobotMap.rightMotor2);
		omniTalon = new CANTalon(RobotMap.omniWheel);

		leftTalon1.set(RobotMap.leftMotor1);
		leftTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftTalon2.set(leftTalon1.getDeviceID());

		rightTalon1.set(RobotMap.rightMotor1);
		rightTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightTalon2.set(rightTalon1.getDeviceID());

		
		leftTalon1.setEncPosition(0);
		rightTalon1.setEncPosition(0);
		
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void drive(double left, double right, double omni) {
		leftTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		omniTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);

		leftTalon1.set(speedMultiple * left);
		rightTalon1.set(-speedMultiple * right);
		omniTalon.set(speedMultiple * omni);
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

	public void driveDistance(double inches) 
	{
//		encLeft.reset();
//		encRight.reset();
		double numTicks = ((inches / inchesPerTick) / 360) * talonConversion;
		rightTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)
		leftTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)



		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		rightTalon1.setEncPosition(0);
		rightTalon1.reverseSensor(true);
		//rightTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)
		rightTalon1.enableControl(); //Enable PID control on the talon

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
		leftTalon1.setEncPosition(0);
		leftTalon1.reverseSensor(true);
		//leftTalon1.setPID(P,I,D); //Set the PID constants (p, i, d)
		leftTalon1.enableControl(); //Enable PID control on the talon
		
		
		leftTalon1.set(-numTicks);
		rightTalon1.set(numTicks);
	}

	public void pivotRight(double degrees) 
	{
		double pivotCircumference = 2 * Math.PI * pivotRadius;
		double pivotArcLength = (degrees / 360) * pivotCircumference;
		double numTicks = ((pivotArcLength / inchesPerTick) / 360) * talonConversion;

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.setEncPosition(0);

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.setEncPosition(0);

		leftTalon1.setPID(P,I,D);
		rightTalon1.setPID(P,I,D);

		leftTalon1.set(-numTicks);
		rightTalon1.set(-numTicks);
	}

	public void pivotLeft(double degrees) 
	{
		double pivotCircumference = 2 * Math.PI * pivotRadius;
		double pivotArcLength = (degrees / 360) * pivotCircumference;
		double numTicks = ((pivotArcLength / inchesPerTick) / 360) * talonConversion;

		leftTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		leftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftTalon1.setEncPosition(0);

		rightTalon1.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightTalon1.setEncPosition(0);

		leftTalon1.setPID(P,I,D);
		rightTalon1.setPID(P,I,D);

		leftTalon1.set(numTicks);
		rightTalon1.set(numTicks);
	}

	public DriveState getDriveType()
	{
		return current;
	}

	public void setDriveType(DriveState x)
	{
		current = x;
	}

	public String toString()
	{
		return (getDriveType().equals(DriveState.ALLWHEEL))?
				("All Wheel"):((getDriveType().equals(DriveState.FOURWHEEL))?
						("Four Wheel"):((getDriveType().equals(DriveState.OMNIWHEEL))?
								("Omni Wheel"):
									("No Wheel")));
	}
}
