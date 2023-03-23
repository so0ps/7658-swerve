package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auton extends SequentialCommandGroup {
    
    public Auton()
    {
        addCommands(
            //new Drive(0.2, 2.0, 0),
            new Drive(0.2, 2.0, 1),
            new Balance()
            );
    }
}
