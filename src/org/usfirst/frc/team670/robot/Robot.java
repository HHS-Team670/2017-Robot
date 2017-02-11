package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team670.robot.commands.AlignWithGear;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoBaseline;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoCenterGear;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoCenterGear_Vision;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoLeftGear_Vision;
import org.usfirst.frc.team670.robot.commands.autonomous.AutoRightGear_Vision;
import org.usfirst.frc.team670.robot.subsystems.Camera;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Dumper;
import org.usfirst.frc.team670.robot.subsystems.Climber;
import org.usfirst.frc.team670.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team670.robot.subsystems.Intake;
import org.usfirst.frc.team670.robot.utilities.NetworkTablesServer;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private static NetworkTablesServer vision;
	public static OI oi;
	public static DriveBase driveBase = new DriveBase();
	public static Camera camera = new Camera();
	//public static DistanceSensor distanceSensor = new DistanceSensor();
	public static Intake intake = new Intake();
	public static Dumper dumper = new Dumper();
	public static Climber climber = new Climber();
	
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	vision = new NetworkTablesServer("vision");
		oi = new OI();
        chooser = new SendableChooser();
        
        chooser.addDefault("Align with gear (TESTING)", new AlignWithGear());
        chooser.addObject("Do Nothing (0 pts)", new AutoDoNothing());
        chooser.addObject("Baseline Auto (5pts)", new AutoBaseline());
        chooser.addObject("Center Gear W/O vision (60pts)", new AutoCenterGear());
        chooser.addObject("Center Gear ~ Vision (60pts)", new AutoCenterGear_Vision());
        chooser.addObject("Left Gear ~ Vision (60pts)", new AutoLeftGear_Vision());
        chooser.addObject("Right Gear ~ Vision (60pts)", new AutoRightGear_Vision());
                
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
    	String yAxis = (climber.shouldRunClimber)?("Climber"):("Shooter");
    	String xAxis = "Intake";
    	
       	SmartDashboard.putString("Drive type:", driveType);
       	//SmartDashboard.putString("Current Distance:", ""+distanceSensor.getDistanceInches());
    	SmartDashboard.putString("Operator X-Axis", xAxis);
    	SmartDashboard.putString("Operator Y-Axis", yAxis);        
        SmartDashboard.putString("Vision System:", (vision.isConnected())?("Running"):("FAILURE"));
	}
    
    public static String getData()
    {
    	return vision.getData("data");
    }
}
