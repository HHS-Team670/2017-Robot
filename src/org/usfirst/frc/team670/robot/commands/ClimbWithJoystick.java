package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.enums.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbWithJoystick extends Command {

    public ClimbWithJoystick() {
    	requires(Robot.climber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getOS().equals(OperatorState.CLIMBER))
    	{
    		double value = -Robot.oi.getOperatorStick().getY();
    		if(value >= 0)
    		{
    			Robot.climber.climb(-value);
    		}
    		else
    		{
    			Robot.climber.climb(0);
    		}
    	}
    	else if(Robot.oi.getOS().equals(OperatorState.REVERSECLIMBER))
    	{
    		double value = -Robot.oi.getOperatorStick().getY();
    		if(value >= 0)
    		{
    			Robot.climber.climb(value);
    		}
    		else
    		{
    			Robot.climber.climb(0);
    		}
    	}
    	else
    		Robot.climber.climb(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.climb(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
