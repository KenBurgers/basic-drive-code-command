package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeoSubsystem;


public class Auto extends SequentialCommandGroup{
    public Auto(DriveSubsystem driveSubsystem, NeoSubsystem neoSubsystem) {
        addCommands(
            
            new RunCommand(() -> driveSubsystem.drive(-0.5, 0), driveSubsystem)
                .withTimeout(0.75),
            new RunCommand(() -> driveSubsystem.drive(0, 0), driveSubsystem)
                .withTimeout(0.1),
            new RunCommand(() -> neoSubsystem.spin())
                .withTimeout(0.1),
            new RunCommand(() -> neoSubsystem.stop())

        );
    }
}
