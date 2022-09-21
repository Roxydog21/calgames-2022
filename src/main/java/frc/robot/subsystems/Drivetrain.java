// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends SubsystemBase {
  /*
  todo: get the json files or whatveer associated with the motors
  */

  // talon srx, victor spx, victor spx, talon srx
  public static final int leftUpperMotorID = 3;
  public static final int leftLowerMotorID = 7;
  public static final int rightLowerMotorID = 8;
  public static final int rightUpperMotorId = 6;
  
  // change to wpi_talon to avoid inheritance problems 
  // may need to add final stuff
  private WPI_TalonSRX leftTalon = new WPI_TalonSRX(leftUpperMotorID);
  private WPI_TalonSRX rightTalon = new WPI_TalonSRX(rightUpperMotorId);
  private VictorSPX leftVictor = new VictorSPX(leftLowerMotorID);
  private VictorSPX rightVictor = new VictorSPX(rightLowerMotorID);

  //via encapsulation
  private DifferentialDrive m_differentialDrive = new DifferentialDrive(leftTalon, rightTalon);
  private Joystick driveJoystick;

  /** Creates a new ExampleSubsystem. */
  public Drivetrain(Joystick joystick) {
    driveJoystick = joystick;


    leftVictor.follow(leftTalon);
    rightVictor.follow(rightTalon);

    // must invert all 4 motors 
    // why invert? https://docs.wpilib.org/en/stable/docs/software/hardware-apis/motors/wpi-drive-classes.html?#axis-conventions - look here
    // simply, ccw rotations are positive, and ccw goes backwards
    leftTalon.setInverted(true);
    rightTalon.setInverted(true);
    /*
    leftVictor.setInverted(true);
    rightVictor.setInverted(true);
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
    m_differentialDrive.arcadeDrive(-driveJoystick.getY(), driveJoystick.getX());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
