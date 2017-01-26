package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DistanceSensor extends Subsystem {
    
	Ultrasonic ultra;
	
	public DistanceSensor()
	{
		try
		{
			ultra = new Ultrasonic(RobotMap.UltrasonicDO, RobotMap.UltrasonicDI);
		}
		catch(Exception e)
		{
			ultra = null;
		}
	}
	
	public double getDistanceInches()
	{
		if(ultra == null)
			return -1;
		else
			return ultra.getRangeInches();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

