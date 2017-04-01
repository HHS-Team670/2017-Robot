package org.usfirst.frc.team670.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap 
{
//RightMotor1 = 3, RightMotor2 = 4, leftMotor2 = 2, leftMotor1 = 1
	//DriveBase
    public static final int rightMotor1 = 3;//HOT
    public static final int rightMotor2 = 4;
    public static final int leftMotor2 = 2;
    public static final int leftMotor1 = 1;//HOT
    public final static int omniWheel = 5;
    
    //Action motors
    public final static int intakeMotor = 0;
    public final static int dumperMotor = 1;
    public final static int climberMotor = 6;
    //Joysticks 
    public final static int operatorStick = 0;
    public final static int leftDriveStick = 1;
    public final static int rightDriveStick = 2;
    public final static int arcButtons = 3;
    
}
