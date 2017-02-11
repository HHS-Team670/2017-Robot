package org.usfirst.frc.team670.robot.subsystems;

import java.nio.file.Files;

import org.usfirst.frc.team670.robot.RobotMap;

import edu.wpi.first.wpilibj.AccumulatorResult;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DistanceSensor extends Subsystem {
    
	private AnalogInput analog;
	private double inchesProportion = 24/0.5358886122703552;
	private double cmToInches = 2.54;
	
	public DistanceSensor()
	{
		analog = new AnalogInput(RobotMap.UltrasonicAI);
	}
	
	public String getDistanceInches()
	{
		 return Double.toString(getVoltage()*inchesProportion).substring(0, 5);
	}
	
	public double getVoltage()
	{
		return analog.getAverageVoltage();
	}
    
	public String getDistanceCM()
	{
		 return Double.toString(getVoltage()*inchesProportion*cmToInches).substring(0, 5);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

