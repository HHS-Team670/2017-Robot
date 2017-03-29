package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Joystick_Climb;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;

import com.ctre.*;

public class Climber extends Subsystem {
	
	CANTalon grapplerMotor = new CANTalon(RobotMap.climberMotor);
	
    public void initDefaultCommand() {
        setDefaultCommand(new Joystick_Climb());
    }
    
    public void climb(double value){
    	grapplerMotor.set(value);
    }
}

