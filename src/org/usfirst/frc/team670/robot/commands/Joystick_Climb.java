package org.usfirst.frc.team670.robot.commands;

import org.usfirst.frc.team670.robot.Robot;
import org.usfirst.frc.team670.robot.utilities.OperatorState;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.PDPJNI;

/**
 *
 */
public class Joystick_Climb extends Command {

	//PowerDistributionPanel pdp = new PowerDistributionPanel();
//	private double current = pdp.getCurrent(12);
//	private double maxPowerSpec = 337;
//	private double batteryVoltage = pdp.getVoltage();
//	private double currentLimit = maxPowerSpec/batteryVoltage;

//	private boolean stopped = false;
//	public static boolean working = true;

	public Joystick_Climb() {
		requires(Robot.climber);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.err.println(current);
//		current = pdp.getCurrent(12);
//		batteryVoltage = pdp.getVoltage();
//		currentLimit = maxPowerSpec/batteryVoltage;

		if(Robot.oi.getOS().equals(OperatorState.CLIMBER))
		{
			double value = -Robot.oi.getOperatorStick().getY();
//			if(stopped == false)
//			{
//				working = true;
//				if(value >= 0)
//				{
					Robot.climber.climb(-value);
//				}
//				else
//				{
//					Robot.climber.climb(0);
//				}
//			}
//			else
//			{
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				stopped = false;
//				working = true;
//			}
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
//		if(current >= currentLimit)
//		{
//			stopped = true;
//			working = false;
//			return true;
//		}
//		else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.climber.climb(0);
	}
	
//	public static boolean isWorking()
//	{
//		return working;
//	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
