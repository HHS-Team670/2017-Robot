package org.usfirst.frc.team670.robot.subsystems;

import org.usfirst.frc.team670.robot.RobotMap;
import org.usfirst.frc.team670.robot.commands.ClimbWithJoystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.*;

public class Climber extends Subsystem {
	
	CANTalon grapplerMotor = new CANTalon(RobotMap.climberMotor);
	
	public boolean shouldRunClimber = false;

    public void initDefaultCommand() {
        setDefaultCommand(new ClimbWithJoystick());
    }
    
    public void runClimber(double value){
    	grapplerMotor.set(value);
    }

	public void setClimbMode(boolean b) {
		shouldRunClimber = b;
	}
}

