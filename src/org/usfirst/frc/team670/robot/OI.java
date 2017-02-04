package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.ToggleClimber;
import org.usfirst.frc.team670.robot.commands.camera.FlipCamera;
import org.usfirst.frc.team670.robot.utilities.DriveState;
import org.usfirst.frc.team670.robot.commands.ChangeDriveType;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.PlaceGear;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
		
	//Joysticks
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	//Operator Buttons
	private Button toggleClimber = new JoystickButton(operatorStick, 1);
	private Button placeGear = new JoystickButton(operatorStick, 3);
	private Button cancelCommand = new JoystickButton(operatorStick, 5);
	
	//Driver Controls
	private Button runOmniDrive = new JoystickButton(rightDriveStick, 3);
	private Button runAllWheel = new JoystickButton(leftDriveStick, 3);
	private Button flipCamera = new JoystickButton(rightDriveStick, 4);	

	//Arcade buttons
	
	public OI(){
		
		runOmniDrive.whenPressed(new ChangeDriveType(DriveState.OMNIWHEEL));
		runOmniDrive.whenReleased(new ChangeDriveType(DriveState.FOURWHEEL));

		runAllWheel.whenPressed(new ChangeDriveType(DriveState.ALLWHEEL));
		runAllWheel.whenReleased(new ChangeDriveType(DriveState.FOURWHEEL));
		
		placeGear.whenPressed(new PlaceGear(12));
		
		flipCamera.whenPressed(new FlipCamera());
		
		toggleClimber.whenPressed(new ToggleClimber());
		
		cancelCommand.whenPressed(new CancelCommand());
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