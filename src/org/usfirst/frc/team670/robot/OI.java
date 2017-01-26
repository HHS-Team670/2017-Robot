package org.usfirst.frc.team670.robot;

import org.usfirst.frc.team670.robot.commands.RunDumper;
import org.usfirst.frc.team670.robot.commands.DoNothing;
import org.usfirst.frc.team670.robot.commands.FlipControls;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	//Joysticks
	private Joystick leftDriveStick = new Joystick(RobotMap.leftDriveStick);
	private Joystick rightDriveStick = new Joystick(RobotMap.rightDriveStick);
	private Joystick operatorStick = new Joystick(RobotMap.operatorStick);
	private Joystick arcButtons = new Joystick(RobotMap.arcButtons);
	
	private Button cancel = new JoystickButton(arcButtons, 1);
	private Button button2 = new JoystickButton(arcButtons, 2);
	private Button button3 = new JoystickButton(arcButtons, 3);
	private Button button4 = new JoystickButton(arcButtons, 4);
	private Button button5 = new JoystickButton(arcButtons, 5);
	private Button button6 = new JoystickButton(arcButtons, 6);
	private Button button7 = new JoystickButton(arcButtons, 7);
	private Button button8 = new JoystickButton(arcButtons, 8);
	private Button button9 = new JoystickButton(arcButtons, 9);
	
	private Button runDumper = new JoystickButton(operatorStick, 1);
	
	private Button flipControls = new JoystickButton(leftDriveStick, 1);
	/*private Button cancel = new JoystickButton(operatorStick, 1);
	private Button startPosDriveModeButt = new JoystickButton(rightDriveStick, 3);
	private Button endPosDriveModeButt = new JoystickButton(rightDriveStick, 2);
	private Button startPosDriveButt = new JoystickButton(rightDriveStick, 1);*/
	
	public OI(){
		//Flipping controls and camera
		flipControls.whenPressed(new FlipControls());
		
		//Run the dumper for the low goal
		runDumper.whenPressed(new RunDumper(100));
		runDumper.whenReleased(new RunDumper(0));
		
		
		cancel.whenReleased(new DoNothing());
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