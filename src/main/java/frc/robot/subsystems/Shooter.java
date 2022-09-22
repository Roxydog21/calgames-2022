// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  public static final double kRunningSpeed = 0.75;
  private final CANSparkMax m_motor = new CANSparkMax(7, MotorType.kBrushless);

  private final Servo m_servo = new Servo(4);

  public boolean getUpperLimit() {
    return m_motor.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
  }

  public boolean getLowerLimit() {
    return m_motor.getForwardLimitSwitch(Type.kNormallyOpen).isPressed();
  }

  public void setMotor(double s) {
    m_motor.set(s);
  }

  public void openServo() {
    m_servo.set(0.0);
  }

  public void closeServo() {
    m_servo.set(1.0);
  }
}
