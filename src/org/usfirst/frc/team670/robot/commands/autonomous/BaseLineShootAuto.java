package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.commands.Time_AutoShoot;
import org.usfirst.frc.team670.robot.commands.CancelCommand;
import org.usfirst.frc.team670.robot.commands.Time_AutoOmni;
import org.usfirst.frc.team670.robot.commands.Time_AutoPivot;
import org.usfirst.frc.team670.robot.commands.Time_AutoTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Start touching the boiler, move and shoot into the low goal, then back up to baseline
 */
public class BaseLineShootAuto extends CommandGroup {

	double speed = 0.50;
	double Approachspeed = 0.35;
	
    public BaseLineShootAuto(char alliance) {
    	if(alliance == 'b')
    	{
    		//Move omni right by 2 seconds
    		addSequential(new Time_AutoOmni(0.75, -speed));
    		
    		addSequential(new Time_AutoShoot(2.5, 1));
    		//HEre is the point where we are in front of the goal, and balls are shot in
    		addSequential(new Time_AutoTank(0.5, -speed));

    		addSequential(new Time_AutoOmni(0.9, -speed));

    		addSequential(new Time_AutoTank(3, -Approachspeed));
    		addSequential(new Time_AutoTank(5, -Approachspeed*0.5));
    	}
    	else if(alliance == 'r')
    	{
    		//Move omni left by 2 seconds
    		addSequential(new Time_AutoOmni(0.75, speed));
    		addSequential(new Time_AutoShoot(2.5, 1));
    		//HEre is the point where we are in front of the goal, and balls are shot in
    		
    		addSequential(new Time_AutoTank(0.5, -speed));
    		
    		addSequential(new Time_AutoPivot(3, speed));
    		
    		addSequential(new Time_AutoOmni(0.9,speed));

    		addSequential(new Time_AutoTank(3, -Approachspeed));
    		addSequential(new Time_AutoTank(5, -Approachspeed*0.5));
    	}
    	else{}
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
