package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OmniDriveTime extends Command {
	
	private double seconds = 0;
	private char direction = ' ';
	private double speed = 0;
	//'l' for left, 'r' for right
    public OmniDriveTime(double seconds, char direction, double speed) {
    	this.direction = direction;
    	this.seconds = seconds;
    	this.speed = speed;
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	setTimeout(seconds);
    	if(direction == 'l')
    		Robot.driveBase.drive(0, 0, -speed);
    	else if(direction == 'r')
    		Robot.driveBase.drive(0, 0, speed);
    	else
    		Robot.driveBase.drive(0, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
