package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Climb;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grappler extends Subsystem {
	
	CANTalon grapplerMotor = new CANTalon(RobotMap.grapplerMotor);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new Climb());
    }
    
    public void runGrappler(double value){
    	grapplerMotor.set(value);
    }
}

