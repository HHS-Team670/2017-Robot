package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.enums.OperatorState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWithJoystick extends Command {

    public IntakeWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	requires(Robot.intake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getOS().equals(OperatorState.INTAKE))
    		Robot.intake.intake(-Robot.oi.getOperatorStick().getY());
    	else
    		Robot.intake.intake(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intake(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
