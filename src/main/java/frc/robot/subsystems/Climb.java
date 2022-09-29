// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  private Solenoid m_arm = new Solenoid(1, PneumaticsModuleType.CTREPCM, 5);

  /** Creates a new Climb. */
  public Climb() {}

  public InstantCommand getUp() {
    return new InstantCommand(() -> m_arm.set(true), this);
  } 

  public InstantCommand getDown() {
    return new InstantCommand(() -> m_arm.set(false), this);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
