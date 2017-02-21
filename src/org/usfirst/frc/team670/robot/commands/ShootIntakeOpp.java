package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.enums.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootIntakeOpp extends Command {

    public ShootIntakeOpp() {
       requires(Robot.shooter);
       requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getOS().equals(OperatorState.INTAKESHOOTOPP))
		{
			Robot.intake.intake(Robot.oi.getOperatorStick().getY());
			Robot.shooter.shoot(Robot.oi.getOperatorStick().getY());
		}
		else
		{
			Robot.intake.intake(0);
			Robot.shooter.shoot(0);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intake(0);
    	Robot.shooter.shoot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
