package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class NeoSubsystem extends SubsystemBase {

    private final SparkMax neoTest =
        new SparkMax(RobotMap.testNeo, MotorType.kBrushless);

    public void spin() {
        neoTest.set(0.5);
    }

    public void stop() {
        neoTest.set(0);
    }
}
