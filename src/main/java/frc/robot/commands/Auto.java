package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NeoSubsystem;
import frc.robot.subsystems.VisionInterface;



public class Auto extends SequentialCommandGroup {

    public Auto(DriveSubsystem driveSubsystem, VisionInterface vision) {

        addCommands(

            new RunCommand(() -> {
                
                double error = vision.getStrafeError();
                System.out.println(error);
                if (error > 0.3) {
                    driveSubsystem.drive(0.5, 0.5);
                } else if (error < -0.3) {
                    driveSubsystem.drive(0.5, -0.5);
                } else {
                    driveSubsystem.drive(0, 0);
                }

            }, driveSubsystem).withTimeout(100)

        );
    }
}