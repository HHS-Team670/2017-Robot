package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.RunDumper;
import org.usfirst.frc.team670.robot.commands.camera.FlipCamera;
import org.usfirst.frc.team670.robot.commands.ChangeDriveType;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
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
	private Button runDumper = new JoystickButton(operatorStick, 1);
	private Button placeGear = new JoystickButton(operatorStick, 3);
	private Button cancelCommand = new JoystickButton(operatorStick, 5);
	
	//Driver Controls
	private Button runOmniDrive = new JoystickButton(rightDriveStick, 3);

	//Arcade buttons
	private Button flipControls = new JoystickButton(arcButtons, 1);	
	
	public OI(){
		
		runOmniDrive.whenPressed(new ChangeDriveType(true));
		runOmniDrive.whenReleased(new ChangeDriveType(false));

		placeGear.whenPressed(new PlaceGear(0));
		
		flipControls.whenPressed(new FlipCamera());
		
		runDumper.whenPressed(new RunDumper(100));
		runDumper.whenReleased(new RunDumper(0));
		
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