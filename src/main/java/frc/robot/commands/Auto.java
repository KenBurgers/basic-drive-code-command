package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionInterface;

public class Auto extends SequentialCommandGroup {

    private static final double kP = 1.2;
    private static final double kI = 0.0;
    private static final double kD = 0.1;

    private static final double MAX_ROTATION = 0.6;

    public Auto(DriveSubsystem driveSubsystem, VisionInterface vision) {

        PIDController pid = new PIDController(kP, kI, kD);
        pid.setTolerance(0.05);
        pid.setSetpoint(0); // center on the tag

        addCommands(

            new RunCommand(() -> {

                // Stop if tag disappears
                if (!vision.hasTarget()) {
                    driveSubsystem.stop();
                    pid.reset();
                    return;
                }

                double error = vision.getStrafeError();

                double output = pid.calculate(error);

                output = MathUtil.clamp(output, -MAX_ROTATION, MAX_ROTATION);

                // rotate robot toward tag
                driveSubsystem.drive(0, output);

            }, driveSubsystem)

        );
    }
}