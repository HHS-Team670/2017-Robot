package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensor extends Subsystem {
    
	private AnalogInput analog;
	private double voltageProportionInches = 44.7854264;
	private double cmToInches = 2.54;
	
	public DistanceSensor()
	{
		analog = new AnalogInput(RobotMap.UltrasonicAI);
	}
	
	public Double getDistanceInches()
	{
		 return getVoltage()*voltageProportionInches;
	}
	
	public double getVoltage()
	{
		return analog.getVoltage();
	}
    
	public Double getDistanceCM()
	{
		 return getDistanceInches()*cmToInches;

	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

