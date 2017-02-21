package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToWallAuto extends Command {

	boolean cancel = false;
	
    public DriveToWallAuto() {
    	requires(Robot.distanceSensor);
    	requires(Robot.driveBase);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double speed = 0.3;
    	if(Robot.distanceSensor.getDistanceInches() >= 12)
    	{
    		Robot.driveBase.drive(speed, speed, 0);
    	}
    	else
    	{
    		Robot.driveBase.driveDistance(14);
    		cancel = true;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
