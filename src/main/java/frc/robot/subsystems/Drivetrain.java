// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  /*
   * todo: get the json files or whatveer associated with the motors
   */

  // talon srx, victor spx, victor spx, talon srx
  public static final int leftUpperMotorID = 4;
  public static final int leftLowerMotorID = 3;
  public static final int rightLowerMotorID = 1;
  public static final int rightUpperMotorId = 2;

  // change to wpi_talon to avoid inheritance problems
  // may need to add final stuff
  private WPI_TalonFX m_leftPrimary = new WPI_TalonFX(leftUpperMotorID);
  private WPI_TalonFX m_rightPrimary = new WPI_TalonFX(rightUpperMotorId);
  private WPI_TalonFX m_leftSecondary = new WPI_TalonFX(leftLowerMotorID);
  private WPI_TalonFX m_rightSecondary = new WPI_TalonFX(rightLowerMotorID);

  // via encapsulation
  private DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftPrimary, m_rightPrimary);
  private Joystick driveJoystick;

  /** Creates a new ExampleSubsystem. */
  public Drivetrain(Joystick joystick) {
    driveJoystick = joystick;

    // leftVictor.follow(leftTalon);
    // rightVictor.follow(rightTalon);
    m_leftPrimary.setInverted(false);
    m_rightPrimary.setInverted(true);
    m_leftSecondary.setInverted(false);
    m_rightSecondary.setInverted(true);

    m_leftSecondary.follow(m_leftPrimary);
    m_rightSecondary.follow(m_rightPrimary);

    // must invert all 4 motors
    // why invert?
    // https://docs.wpilib.org/en/stable/docs/software/hardware-apis/motors/wpi-drive-classes.html?#axis-conventions
    // - look here
    // simply, ccw rotations are positive, and ccw goes backwards
    // m_leftPrimary.setInverted(true);
    // m_rightPrimary.setInverted(true);
    /*
     * leftVictor.setInverted(true);
     * rightVictor.setInverted(true);
     */

    // test call to method
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
}
