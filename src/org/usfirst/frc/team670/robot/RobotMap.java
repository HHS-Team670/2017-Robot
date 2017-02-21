package org.usfirst.frc.team670.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap 
{

	//DriveBase
    public final static int rightMotor1 = 3;
    public final static int rightMotor2 = 4;
    public final static int leftMotor2 = 2;
    public final static int leftMotor1 = 1;
    public final static int omniWheel = 5;
    
    //Action motors
    public final static int intakeMotor = 1;
    public final static int dumperMotor = 0;
    public final static int climberMotor = 6;
  
    //Sensor ports
    public final static int UltrasonicAI = 0;

    //Joysticks + Buttons
    public final static int operatorStick = 0;
    public final static int leftDriveStick = 1;
    public final static int rightDriveStick = 2;
    public final static int arcButtons = 3;
    
}
