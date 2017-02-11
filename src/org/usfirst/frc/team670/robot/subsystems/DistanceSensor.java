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
	private double voltage = 0.5358886122703552;
	private double cmToInches = 2.54;
	
	public DistanceSensor()
	{
		analog = new AnalogInput(RobotMap.UltrasonicAI);
	}
	
	public Double getDistanceInches()
	{
		 return (24*(getVoltage()/voltage));
	}
	
	public double getVoltage()
	{
		return analog.getVoltage();
	}
    
	public String getDistanceCM()
	{
		 return Double.toString((getDistanceInches())*cmToInches).substring(0, 5);

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

