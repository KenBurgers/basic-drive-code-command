package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NeoSubsystem;

/** Simple command that runs the Neo motor in reverse while held. */
public class NeoReverseCommand extends Command {

    private final NeoSubsystem neoSubsystem;

    public NeoReverseCommand(NeoSubsystem neoSubsystem) {
        this.neoSubsystem = neoSubsystem;
        addRequirements(neoSubsystem);
    }

    @Override
    public void execute() {
        neoSubsystem.spinReverse();
    }

    @Override
    public void end(boolean interrupted) {
        neoSubsystem.stop();
    }
}
