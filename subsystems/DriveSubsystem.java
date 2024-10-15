package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
    private final VictorSPX leftMotor1 = new VictorSPX(Constants.LEFT_MOTOR_1_ID);
    private final VictorSPX leftMotor2 = new VictorSPX(Constants.LEFT_MOTOR_2_ID);
    private final VictorSPX rightMotor1 = new VictorSPX(Constants.RIGHT_MOTOR_1_ID);
    private final VictorSPX rightMotor2 = new VictorSPX(Constants.RIGHT_MOTOR_2_ID);

    private double LS, RS;

    public DriveSubsystem() {
        rightMotor1.setInverted(true);
        rightMotor2.setInverted(true);
    }

    public void setMotorSpeeds(double leftSpeed, double rightSpeed) {
        LS = leftSpeed;
        RS = rightSpeed;
        leftMotor1.set(ControlMode.PercentOutput, LS);
        leftMotor2.set(ControlMode.PercentOutput, LS);
        rightMotor1.set(ControlMode.PercentOutput, RS);
        rightMotor2.set(ControlMode.PercentOutput, RS);

        updateSmartDashboard();
    }

    private void updateSmartDashboard() {
        SmartDashboard.putNumber("Left Motor Speed", LS);
        SmartDashboard.putNumber("Right Motor Speed", RS);
    }
}
