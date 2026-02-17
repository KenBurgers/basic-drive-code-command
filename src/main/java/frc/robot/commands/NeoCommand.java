package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NeoSubsystem;

public class NeoCommand extends Command {

    private final NeoSubsystem neoSubsystem;

    public NeoCommand(NeoSubsystem neoSubsystem) {
        this.neoSubsystem = neoSubsystem;
        addRequirements(neoSubsystem);
    }

    @Override
    public void execute() {
        neoSubsystem.spin();
    }

    @Override
    public void end(boolean interrupted) {
        neoSubsystem.stop();
    }
}
