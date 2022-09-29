// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  // talon srx, victor spx, victor spx, talon srx
  public static final int leftUpperMotorID = 4;
  public static final int leftLowerMotorID = 3;
  public static final int rightLowerMotorID = 1;
  public static final int rightUpperMotorId = 2;
  
  public static final int lowGearSolenoid = 6;
  public static final int highGearSolenoid = 1;

  // change to wpi_talon to avoid inheritance problems
  // may need to add final stuff
  private WPI_TalonFX m_leftPrimary = new WPI_TalonFX(leftUpperMotorID);
  private WPI_TalonFX m_rightPrimary = new WPI_TalonFX(rightUpperMotorId);
  private WPI_TalonFX m_leftSecondary = new WPI_TalonFX(leftLowerMotorID);
  private WPI_TalonFX m_rightSecondary = new WPI_TalonFX(rightLowerMotorID);

  // via encapsulation
  private DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftPrimary, m_rightPrimary);

  private DoubleSolenoid m_shifters = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, highGearSolenoid, lowGearSolenoid);

  /** Creates a new ExampleSubsystem. */
  public Drivetrain() {
    m_leftPrimary.setInverted(false);
    m_leftSecondary.setInverted(false);
    m_rightPrimary.setInverted(true);
    m_rightSecondary.setInverted(true);

    m_leftSecondary.follow(m_leftPrimary);
    m_rightSecondary.follow(m_rightPrimary);

    m_leftPrimary.setNeutralMode(NeutralMode.Coast);
    m_leftSecondary.setNeutralMode(NeutralMode.Brake);
    m_rightPrimary.setNeutralMode(NeutralMode.Coast);
    m_rightSecondary.setNeutralMode(NeutralMode.Brake);
  }

  // not sure why i need this but joystick stuff calls for it por something
  public DifferentialDrive getDiffDrive() {
    return m_differentialDrive;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // add drive stuff
    // m_differentialDrive.arcadeDrive(-driveJoystick.getY(), driveJoystick.getX());
    SmartDashboard.putData(m_differentialDrive);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public InstantCommand getHighGear() {
    return new InstantCommand(() -> m_shifters.set(Value.kForward));
  }

  public InstantCommand getLowGear() {
    return new InstantCommand(() -> m_shifters.set(Value.kReverse));
  }
}
