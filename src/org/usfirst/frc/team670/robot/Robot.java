package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team670.robot.commands.DriveDistance;
import org.usfirst.frc.team670.robot.commands.PivotLeft;
import org.usfirst.frc.team670.robot.commands.PivotRight;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoBaseline;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team670.robot.commands.autonomous.CenterGearLeft;
import org.usfirst.frc.team670.robot.commands.autonomous.CenterGearRight;
import org.usfirst.frc.team670.robot.commands.autonomous.LeftGear;
import org.usfirst.frc.team670.robot.commands.autonomous.LeftGearCenter;
import org.usfirst.frc.team670.robot.commands.autonomous.RightGear;
import org.usfirst.frc.team670.robot.commands.autonomous.RightGearCenter;
import org.usfirst.frc.team670.robot.enums.OperatorState;
import org.usfirst.frc.team670.robot.subsystems.Camera;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Shooter;
import org.usfirst.frc.team670.robot.subsystems.Climber;
import org.usfirst.frc.team670.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team670.robot.subsystems.Intake;
import org.usfirst.frc.team670.robot.vision.NetworkTablesServer;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private static NetworkTablesServer vision;
	public static OI oi;
	public static DriveBase driveBase = new DriveBase();
	public static Camera camera = new Camera();
	public static DistanceSensor distanceSensor = new DistanceSensor();
	public final static Intake intake = new Intake();
	public final static Shooter shooter = new Shooter();
	public static Climber climber = new Climber();
	
	
    Command autonomousCommand;
    SendableChooser<Command> chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	vision = new NetworkTablesServer("vision");
		oi = new OI();
        chooser = new SendableChooser<Command>();
        
        //Commands for testing PID etc.
        chooser.addDefault("Drive 2 ft", new DriveDistance(24));
        chooser.addObject("Pivot 90 degrees right", new PivotRight(90));
        chooser.addObject("Pivot 90 degrees left", new PivotLeft(90));
        
        //add below as default
        chooser.addObject("Baseline Auto (5pts)", new AutoBaseline());
        chooser.addObject("Do Nothing (0 pts)", new AutoDoNothing());
        
        chooser.addObject("Center Gear from Left (60pts)", new CenterGearLeft());
        chooser.addObject("Center Gear from Right (60pts)", new CenterGearRight());
        
        chooser.addObject("Left Gear (60pts)", new LeftGear());
        chooser.addObject("Right Gear (60pts)", new RightGear());
        
        chooser.addObject("Left Gear from Center (60pts)", new LeftGearCenter());
        chooser.addObject("Right Gear from Center (60pts)", new RightGearCenter());
        
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
       	SmartDashboard.putString("Current Distance:", "" + distanceSensor.getDistanceInches());
    	SmartDashboard.putString("Operator Stick", os);
        SmartDashboard.putString("Vision System:", (vision.isConnected())?("Connected"):("FAILURE"));
	}
    
    public static String getData()
    {
    	return vision.getData("data");
    }
}
