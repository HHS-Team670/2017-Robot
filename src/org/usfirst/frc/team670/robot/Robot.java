package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team670.robot.commands.SetDriveControl;
import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.PivotLeft;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoBaseline;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team670.robot.commands.autonomous.BaseLineShootAuto;
import org.usfirst.frc.team670.robot.commands.autonomous.CenterGearLeft;
import org.usfirst.frc.team670.robot.commands.autonomous.CenterGearRight;
import org.usfirst.frc.team670.robot.commands.autonomous.LeftGear;
import org.usfirst.frc.team670.robot.commands.autonomous.LeftGearCenter;
import org.usfirst.frc.team670.robot.commands.autonomous.RightGear;
import org.usfirst.frc.team670.robot.commands.autonomous.RightGearCenter;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Shooter;
import org.usfirst.frc.team670.robot.utilities.DriveState;
import org.usfirst.frc.team670.robot.utilities.OperatorState;
import org.usfirst.frc.team670.robot.subsystems.Camera;
import org.usfirst.frc.team670.robot.subsystems.Climber;
import org.usfirst.frc.team670.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team670.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static OI oi;
	public final static DriveBase driveBase = new DriveBase();
	public final static Camera camera = new Camera();
	public final static DistanceSensor distanceSensor = new DistanceSensor();
	public final static Intake intake = new Intake();
	public final static Shooter shooter = new Shooter();
	public final static Climber climber = new Climber();
	
	//Camera stuff
	
	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		chooser = new SendableChooser<Command>();
		
		new SetDriveControl(DriveState.FOURWHEEL);
		
		//Seconds, then speed
		chooser.addObject("Do Nothing (0 pts)", new AutoDoNothing());
		chooser.addDefault("Baseline Auto (5pts)", new AutoBaseline(1.2, 1));
		//Baseline is just going forward by 10 seconds, Center gear is the exact same thing
		chooser.addObject("Center Gear from Center (60pts)", new AutoBaseline(1, 1));
		//'r' = red alliance, 'b' = blue alliance
		chooser.addObject("Shoot + Baseline ~ Red Alliance (8pts)", new BaseLineShootAuto('r'));
		chooser.addObject("Shoot + Baseline ~ Blue Alliance (8pts)", new BaseLineShootAuto('b'));

		SmartDashboard.putData("Auto mode", chooser);
	}

	public void disabledInit(){

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */

		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		putData();
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		putData();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void putData(){
		String driveType = driveBase.toString();
		String os = (oi.getOS().equals(OperatorState.CLIMBER))?("Climber"):(oi.getOS().equals(OperatorState.SHOOTER))?("Shooter"):(oi.getOS().equals(OperatorState.INTAKE))?("Intake"):(oi.getOS().equals(OperatorState.REVERSECLIMBER))?("Reverse Climber"):(oi.getOS().equals(OperatorState.INTAKESHOOT))?("IN/OUT"):("None");

		SmartDashboard.putString("Drive type:", driveType);
		SmartDashboard.putString("Operator Stick", os);
		SmartDashboard.putString("Controls Flipped?", Boolean.toString(!OI.isControlsStandard));
		//SmartDashboard.putString("Climber Working: ", ClimbWithJoystick.isWorking() + "");
		// SmartDashboard.putString("Cam1:" , Camera.one + "");

	}

	public static String getData()
	{
		return "null";
	}
}
