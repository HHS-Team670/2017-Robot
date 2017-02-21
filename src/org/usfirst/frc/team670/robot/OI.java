package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.SetOperatorCommand;
import org.usfirst.frc.team670.robot.commands.camera.FlipCamera;
import org.usfirst.frc.team670.robot.enums.DriveState;
import org.usfirst.frc.team670.robot.enums.OperatorState;
import org.usfirst.frc.team670.robot.commands.ChangeDriveType;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.DriveToWall;
import org.usfirst.frc.team670.robot.commands.OmniDriveTime;
import org.usfirst.frc.team670.robot.commands.PivotLeft;
import org.usfirst.frc.team670.robot.commands.PivotRight;
import org.usfirst.frc.team670.robot.commands.AlignWithGear;
import org.usfirst.frc.team670.robot.commands.CancelCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	private OperatorState os = OperatorState.NONE;
	public boolean drawRopeReticle = false;
	
	
	//This is the width of driver's side of the field for auto calculations. Need to figure if this is right.
	public double baseWidthInches = 105.75;
	//Decides if we use ultrasonic sensor or not during auto.
	public boolean useUltraSonic = true;
	//Joysticks
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	//Operator Buttons
	private Button toggleClimber = new JoystickButton(operatorStick, 5);
	private Button toggleReverseClimber = new JoystickButton(operatorStick, 8);
	private Button toggleIntake = new JoystickButton(operatorStick, 3);
	private Button toggleShooter = new JoystickButton(operatorStick, 4);
	private Button cancelCommand = new JoystickButton(operatorStick, 9);
	private Button intakeShooterSame = new JoystickButton(operatorStick, 1);
	private Button intakeShooterOpp = new JoystickButton(operatorStick, 2);
	
	//Driver Controls
	private Button runOmniDrive = new JoystickButton(rightDriveStick, 3);
	private Button runAllWheel = new JoystickButton(leftDriveStick, 1);
	private Button flipCamera = new JoystickButton(rightDriveStick, 4);	
	private Button placeGear = new JoystickButton(rightDriveStick, 2);
	private Button ramWall = new JoystickButton(rightDriveStick, 1);
	private Button leftPivot = new JoystickButton(leftDriveStick, 4);
	private Button rightPivot = new JoystickButton(leftDriveStick, 5);
	private Button incrementF = new JoystickButton(leftDriveStick, 3);
	private Button incrementB = new JoystickButton(leftDriveStick, 2);
	private Button leftStrafe = new JoystickButton(rightDriveStick, 4);
	private Button rightStrafe = new JoystickButton(rightDriveStick, 5);

	//Arcade buttons

	
	public OI(){
		
		runOmniDrive.whenPressed(new ChangeDriveType(DriveState.OMNIWHEEL));
		runOmniDrive.whenReleased(new ChangeDriveType(DriveState.FOURWHEEL));
		
		runAllWheel.whenPressed(new ChangeDriveType(DriveState.ALLWHEEL));
		runAllWheel.whenReleased(new ChangeDriveType(DriveState.FOURWHEEL));
		
		placeGear.whenPressed(new AlignWithGear());
		placeGear.whenReleased(new CancelCommand());
		
		ramWall.whenPressed(new DriveToWall());
		ramWall.whenReleased(new CancelCommand());
		
		flipCamera.whenPressed(new FlipCamera());
				
		//Operator Button Commands
		toggleClimber.whenPressed(new SetOperatorCommand(OperatorState.CLIMBER));
		toggleClimber.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		toggleReverseClimber.whenPressed(new SetOperatorCommand(OperatorState.REVERSECLIMBER));
		toggleReverseClimber.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		toggleIntake.whenPressed(new SetOperatorCommand(OperatorState.INTAKE));
		toggleIntake.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		toggleShooter.whenPressed(new SetOperatorCommand(OperatorState.SHOOTER));
		toggleShooter.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		intakeShooterSame.whenPressed(new SetOperatorCommand(OperatorState.INTAKESHOOTSAME));
		intakeShooterSame.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		intakeShooterOpp.whenPressed(new SetOperatorCommand(OperatorState.INTAKESHOOTOPP));
		intakeShooterOpp.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		//Precise Movement Buttons
		leftPivot.whenPressed(new PivotLeft(10));
		rightPivot.whenPressed(new PivotRight(10));
		
		incrementF.whenPressed(new DriveDistance(1));
		incrementB.whenPressed(new DriveDistance(-1));
		
		leftStrafe.whenPressed(new OmniDriveTime(0.25,'l', 0.5));
		rightStrafe.whenPressed(new OmniDriveTime(0.25,'r', 0.5));
		
		cancelCommand.whenPressed(new CancelCommand());
		}
	
	public void setOperatorCommand(OperatorState os)
	{
		this.os = os;
	}
	
	public OperatorState getOS()
	{
		return os;
	}
	
	public Joystick getleftStick(){
		return leftDriveStick;
	}
	
	public Joystick getrightStick(){
		return rightDriveStick;
	}
	
	public Joystick getOperatorStick(){
		return operatorStick;
	}
	
	public Joystick getArcButtons(){
		return arcButtons;
	}
}