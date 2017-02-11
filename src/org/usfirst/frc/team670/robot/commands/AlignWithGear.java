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
    	String move = Robot.getData();
    	
    	double pivotSpeed = 0.2;
    	double speed = 0.25;
    	double angle = 5;

    	if(!move.equals("data_unavailable"))
    	{
	    	if(move.equals("right"))
	    		Robot.driveBase.drive(0, 0, -speed);
	    	else if(move.equals("left"))
	    		Robot.driveBase.drive(0, 0, speed);
	    	else if(move.equals("right_pivot"))
	    	{
	    		Robot.driveBase.drive(-pivotSpeed, pivotSpeed, 0);
	    	}
	    	else if(move.equals("left_pivot"))
	    	{
	    		Robot.driveBase.drive(pivotSpeed, -pivotSpeed, -0);
	    	}
	    	else if(move.equals("centered"))
	    		Robot.driveBase.drive(pivotSpeed, pivotSpeed, 0);
	    	else if(move.equals("cancel"))
	    		this.cancel();
    	}
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
