package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeReverseCommand extends Command {

    private final IntakeSubsystem m_intake;
    private static final double SPIN_ANGLE = 0.0; // always spin 30 degrees forward

    public IntakeReverseCommand(IntakeSubsystem intake) {
        m_intake = intake;
        addRequirements(m_intake);
    }

    @Override
    public void execute() {
        // Move the intake 30 degrees forward
        m_intake.setDeployAngle(SPIN_ANGLE);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stop();
    }
}