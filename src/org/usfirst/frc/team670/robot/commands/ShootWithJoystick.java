package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootWithJoystick extends Command {

    public ShootWithJoystick() {
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    protected void execute() {
    	if(Robot.oi.getOS().equals(OperatorState.SHOOTER))
    		Robot.shooter.shoot(Robot.oi.getOperatorStick().getY());
    	else
    		Robot.shooter.shoot(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.shoot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}