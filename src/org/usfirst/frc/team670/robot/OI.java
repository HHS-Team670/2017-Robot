package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.SetOperatorCommand;
import org.usfirst.frc.team670.robot.commands.ToggleReticle;
import org.usfirst.frc.team670.robot.commands.camera.FlipCamera;
import org.usfirst.frc.team670.robot.extras.DriveState;
import org.usfirst.frc.team670.robot.extras.OperatorState;
import org.usfirst.frc.team670.robot.commands.ChangeDriveType;
import org.usfirst.frc.team670.robot.commands.DriveToWallAuto;
import org.usfirst.frc.team670.robot.commands.AlignWithGear;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.flipControls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	private OperatorState os = OperatorState.NONE;
	public static boolean drawRopeReticle = false;
	public static boolean winchControls = true;
	
	
	//This is the width of driver's side of the field for auto calculations. Need to figure if this is right.
	public double baseWidthInches = 247.6;
	//Decides if we use ultrasonic sensor or not during auto.
	public boolean useUltraSonic = true;
	//Joysticks
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	//Operator Buttons
	private Button intakeShooter = new JoystickButton(operatorStick, 1);
	private Button toggleReticle = new JoystickButton(operatorStick, 2);
	private Button toggleShooter = new JoystickButton(operatorStick, 3);
	private Button toggleIntake = new JoystickButton(operatorStick, 4);
	private Button toggleClimber = new JoystickButton(operatorStick, 5);
	private Button toggleReverseClimber = new JoystickButton(operatorStick, 8);
	private Button cancelCommand = new JoystickButton(operatorStick, 9);
	
	
	//Driver Controls
	private Button runOmniDrive = new JoystickButton(rightDriveStick, 3);	
	private Button placeGear = new JoystickButton(rightDriveStick, 2);
	private Button ramWall = new JoystickButton(rightDriveStick, 4);
	
	private Button flipControls = new JoystickButton(leftDriveStick, 2);
	private Button flipCamera = new JoystickButton(leftDriveStick, 3);

	//Arcade buttons
	
	public OI(){
		
		runOmniDrive.whenPressed(new ChangeDriveType(DriveState.OMNIWHEEL));
		runOmniDrive.whenReleased(new ChangeDriveType(DriveState.FOURWHEEL));
		
		placeGear.whenPressed(new AlignWithGear());
		placeGear.whenReleased(new CancelCommand());
		
		ramWall.whenPressed(new DriveToWallAuto());
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
		
		intakeShooter.whenPressed(new SetOperatorCommand(OperatorState.INTAKESHOOT));
		intakeShooter.whenReleased(new SetOperatorCommand(OperatorState.NONE));
		
		//Precise Movement Buttons
		//leftPivot.whenPressed(new PivotLeft(10));
		//rightPivot.whenPressed(new PivotRight(10));
		
		//incrementF.whenPressed(new DriveDistance(1));
		//incrementB.whenPressed(new DriveDistance(-1));
		
		toggleReticle.whenPressed(new ToggleReticle());

		flipControls.whenPressed(new flipControls());
		
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