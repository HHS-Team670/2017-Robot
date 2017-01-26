package org.usfirst.frc.team670.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


import org.usfirst.frc.team670.robot.commands.GearAlignmentAuto;
import org.usfirst.frc.team670.robot.commands.BaselineAuto;
import org.usfirst.frc.team670.robot.commands.DoNothing;
import org.usfirst.frc.team670.robot.commands.CenterGear;
import org.usfirst.frc.team670.robot.commands.CenterGear_Vision;
import org.usfirst.frc.team670.robot.server.NetworkTablesServer;
import org.usfirst.frc.team670.robot.subsystems.Camera;
import org.usfirst.frc.team670.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team670.robot.subsystems.DriveBase;
import org.usfirst.frc.team670.robot.subsystems.Dumper;
import org.usfirst.frc.team670.robot.subsystems.Grappler;
import org.usfirst.frc.team670.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private static boolean isFlipped = false;
	
	public static NetworkTablesServer ns;
	public static OI oi;
	public static DriveBase driveBase = new DriveBase();
	public static Camera camera = new Camera();
	public static DistanceSensor distanceSensor = new DistanceSensor();
	public static Intake intake = new Intake();
	public static Dumper dumper = new Dumper();
	public static Grappler grappler = new Grappler();
	
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	ns = new NetworkTablesServer();
		oi = new OI();
        chooser = new SendableChooser();
        
        chooser.addDefault("Do Nothing", new DoNothing());
        chooser.addObject("Baseline Auto (5pts)", new BaselineAuto());
        chooser.addObject("Center Gear W/O vision (60pts)", new CenterGear());
        chooser.addObject("Center Gear ~ Vision (60pts)", new GearAlignmentAuto());
        //chooser.addObject("Left Gear ~ Vision (60pts)", new LeftGear_Vision());
        //chooser.addObject("Right Gear ~ Vision (60pts)", new RightGear_Vision());
        
        //Auto gear with vision left, right center
        
        SmartDashboard.putData("Auto mode", chooser);
        
        //Connect with co-processor
        //defineConnection();
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
    	TimeLeft();
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
        SmartDashboard.putString("Movement", getData());
    	TimeLeft();
        Scheduler.getInstance().run();
    }
 
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void TimeLeft(){
    	SmartDashboard.putString("TIME LEFT:", getMatchTime() + " Seconds");
	}
    
    public static void setFlipped(boolean x)
    {
    	isFlipped = x;
    }
    
    public static boolean getFlipped()
    {
    	return isFlipped;
    }
    
    public double getMatchTime()
    {
    	return DriverStation.getInstance().getMatchTime();
    }
    
    public static String getData()
    {
    	if(ns.isConnected())
    		return ns.getData();
    	else
    		return "connection_failed";
    }
    
    
}
