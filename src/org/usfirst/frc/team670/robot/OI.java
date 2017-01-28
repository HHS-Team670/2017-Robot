package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.RunDumper;
import org.usfirst.frc.team670.robot.commands.runOmniDrive;
import org.usfirst.frc.team670.robot.commands.DoNothing;
import org.usfirst.frc.team670.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team670.robot.commands.FlipCamera;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
		
	//Joysticks
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	private Button runDumper = new JoystickButton(operatorStick, 1);
	
	private Button flipControls = new JoystickButton(leftDriveStick, 1);
	
	private Button runOmniDrive = new JoystickButton(rightDriveStick, 3);

	/*private Button cancel = new JoystickButton(operatorStick, 1);
	private Button startPosDriveModeButt = new JoystickButton(rightDriveStick, 3);
	private Button endPosDriveModeButt = new JoystickButton(rightDriveStick, 2);
	private Button startPosDriveButt = new JoystickButton(rightDriveStick, 1);*/
	
	public OI(){
		
		runOmniDrive.whenPressed(new runOmniDrive(true));
		runOmniDrive.whenReleased(new runOmniDrive(false));
		//Flipping controls and camera
		flipControls.whenPressed(new FlipCamera());
		
		//Run the dumper for the low goal
		runDumper.whenPressed(new RunDumper(100));
		runDumper.whenReleased(new RunDumper(0));
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