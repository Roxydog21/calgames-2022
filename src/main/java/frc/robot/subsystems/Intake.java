// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  public static final int rollerMotorId = 1;
  public static final int hopperMotorId = 10;
  public static final int armChannel = 0;

  public static final double intakeSpeed = 0.3;
  public static final double hopperSpeed = 0.8;


  private CANSparkMax m_intakeMotor = new CANSparkMax(rollerMotorId, MotorType.kBrushless);
  private WPI_TalonSRX m_hopperMotor = new WPI_TalonSRX(hopperMotorId);
  private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM, armChannel);


  /** Creates a new Intake. */
  public Intake() {
    m_hopperMotor.setInverted(true);
    m_intakeMotor.setInverted(true);

    m_hopperMotor.setNeutralMode(NeutralMode.Coast);
    m_intakeMotor.setIdleMode(IdleMode.kCoast);
  }

  public void run() {
    m_intakeMotor.set(intakeSpeed);
    m_hopperMotor.set(hopperSpeed);
  }

  public void reverse() {
    m_intakeMotor.set(-intakeSpeed);
    m_hopperMotor.set(-hopperSpeed);
  }

  public void stop() {
    m_intakeMotor.set(0.0);
    m_hopperMotor.set(0.0);
  }

  public InstantCommand getRun() {
    return new InstantCommand(this::run, this);
  }

  public InstantCommand getRev() {
    return new InstantCommand(this::reverse, this);
  }

  public InstantCommand getStop() {
    return new InstantCommand(this::stop, this);
  }

  public InstantCommand getOut() {
    return new InstantCommand(() -> m_arm.set(true), this);
  }

  public InstantCommand getIn() {
    return new InstantCommand(() -> m_arm.set(false), this);
  }
}
