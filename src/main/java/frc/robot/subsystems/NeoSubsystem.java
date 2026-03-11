package frc.robot.subsystems;

import frc.robot.RobotMap;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class NeoSubsystem extends SubsystemBase {

    private final SparkMax rightShooterMotor =
        new SparkMax(RobotMap.rightShooterNeo, MotorType.kBrushless);
    private final SparkMax leftShooterMotor = new SparkMax(RobotMap.leftShooterNeo, MotorType.kBrushless);
    private final SparkMax kickerMotor = new SparkMax(RobotMap.kickerMotor, MotorType.kBrushless);

    private final SparkMaxConfig rightMotorConfig = new SparkMaxConfig();
    private final SparkMaxConfig leftMotorConfig = new SparkMaxConfig();
    private final SparkMaxConfig kickerMotorConfig = new SparkMaxConfig();

    public NeoSubsystem() {
        leftMotorConfig.follow(rightShooterMotor, true);
        leftMotorConfig.apply(leftMotorConfig);
        rightMotorConfig.apply(rightMotorConfig);
        kickerMotorConfig.apply(kickerMotorConfig);
        
        rightShooterMotor.configure(rightMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftShooterMotor.configure(leftMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        kickerMotor.configure(kickerMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void spin() {
        rightShooterMotor.set(1);
        kickerMotor.set(1.0);
    }

    public void spinReverse() {
        // neoTest.set(1);

    }

    public void stop() {
        rightShooterMotor.set(0.0);
        kickerMotor.set(0.0);
    }
}
