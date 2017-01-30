package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

    public DriveWithJoystick() {
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.driveBase.isOmniDriving())
    		Robot.driveBase.omniDrive((Robot.oi.getrightStick().getX()));
    	else if(!Robot.driveBase.isOmniDriving())
    		Robot.driveBase.tankDrive((Robot.oi.getleftStick().getY()), (Robot.oi.getrightStick().getY()));
    	else
    		Robot.driveBase.drive(Robot.oi.getleftStick().getY(), Robot.oi.getrightStick().getY(), Robot.oi.getrightStick().getX());
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
