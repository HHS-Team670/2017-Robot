package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AlignWithGear extends Command {

    public AlignWithGear() {
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String move = Robot.ns.getData();
    	
    	double speed = 0.5;
    	double angle = 5;
    	
    	/*if(move.equals("forward"))
    		Robot.driveBase.drive(speed, speed, 0);
    	else if(move.equals("back"))
    		Robot.driveBase.drive(-speed, -speed, 0);*/
    	if(move.equals("moveRight"))
    		Robot.driveBase.drive(0, 0, -speed);
    	else if(move.equals("moveLeft"))
    		Robot.driveBase.drive(0, 0, speed);
    	else if(move.equals("pivot_left"))
    		Robot.driveBase.pivot(angle);
    	else if(move.equals("pivot_right"))
    		Robot.driveBase.pivot(-angle);
    	else if(move.equals("centered"))
    		System.out.println("ALIGNED");//None
    	else
    		Robot.driveBase.drive(0, 0, 0);
    	
    	SmartDashboard.putString("Co-Processor Status:", move);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
