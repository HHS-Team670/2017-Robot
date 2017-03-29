package org.usfirst.frc.team670.robot.commands.autonomous;

import org.usfirst.frc.team670.robot.commands.Time_AutoShoot;
import org.usfirst.frc.team670.robot.commands.Time_AutoOmni;
import org.usfirst.frc.team670.robot.commands.Time_AutoTank;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class BaseLineShootAuto extends CommandGroup {

    public BaseLineShootAuto(char alliance) {
    	if(alliance == 'b')
    	{
    		addSequential(new Time_AutoOmni(5, 1));
    		addSequential(new Time_AutoShoot(5, 1));
    		addSequential(new Time_AutoTank(5, -1));
    	}
    	else if(alliance == 'r')
    	{
    		addSequential(new Time_AutoOmni(5, -1));
    		addSequential(new Time_AutoShoot(5, 1));
    		addSequential(new Time_AutoTank(5, -1));
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
