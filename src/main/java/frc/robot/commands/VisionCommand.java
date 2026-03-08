package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.VisionInterface;

public class VisionCommand extends Command {

    private final VisionInterface m_vision;

    public VisionCommand(VisionInterface vision) {
        m_vision = vision;
        //addRequirements(m_vision);
    }

    @Override
    public void execute() {
        m_vision.update();
    }
    
}
