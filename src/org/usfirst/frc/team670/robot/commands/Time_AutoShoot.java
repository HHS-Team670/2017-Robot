package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Time_AutoShoot extends Command {
	
	private double seconds = 0, speed = 0;

    public Time_AutoShoot(double seconds, double speed) {
    	this.seconds = seconds;
    	this.speed = speed;
        requires(Robot.shooter);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        setTimeout(seconds);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.shoot(-speed);
        Robot.intake.intake(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stopShooter();
    	Robot.intake.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.shoot(0);
    }
}
