package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.Climb;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.*;
/**
 *
 */
public class Climber extends Subsystem {
	
	CANTalon grapplerMotor = new CANTalon(RobotMap.grapplerMotor);
	public boolean shouldRunClimber = false;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new Climb());
    }
    
    public void runClimber(double value){
    	grapplerMotor.set(value);
    }

	public void setClimbMode(boolean b) {
		shouldRunClimber = b;
	}
}

