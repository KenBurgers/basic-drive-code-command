package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkFlexConfig;

public class IntakeSubsystem extends SubsystemBase {

    private final SparkFlex m_intakeDeploySpark;
    private final RelativeEncoder m_encoder;
    private final SparkClosedLoopController m_pidController;

    // CHANGE THIS to your gear ratio
    private static final double GEAR_RATIO = 1.0; // example: 100:1
    private static final double DEGREES_PER_ROTATION = 360.0;

    public IntakeSubsystem() {

        m_intakeDeploySpark = new SparkFlex(9, MotorType.kBrushless);

        SparkFlexConfig config = new SparkFlexConfig();

        config
            .inverted(false)
            .idleMode(SparkFlexConfig.IdleMode.kBrake)
            .smartCurrentLimit(50);

        // PID values (TUNE THESE)
        config.closedLoop
            .p(0.05)
            .i(0.0)
            .d(0.0);

        m_intakeDeploySpark.configure(config,
            SparkFlex.ResetMode.kResetSafeParameters,
            SparkFlex.PersistMode.kPersistParameters);

        m_encoder = m_intakeDeploySpark.getEncoder();
        m_pidController = m_intakeDeploySpark.getClosedLoopController();

        m_encoder.setPosition(0); // Zero on startup (optional)
    }

    /**
     * Moves intake to a desired angle (in degrees)
     */
    public void setDeployAngle(double angleDegrees) {

        // Convert mechanism angle to motor rotations
        double motorRotations = (angleDegrees / DEGREES_PER_ROTATION) * GEAR_RATIO;

        m_pidController.setReference(motorRotations, ControlType.kPosition);
    }

    public void stop() {
        m_intakeDeploySpark.stopMotor();
    }
}