// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Pause;
import frc.robot.commands.shooting.Launch;
import frc.robot.commands.shooting.Reload;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private static final class Button {
    // actually get the correct button
    private static final int shootButton = 8;
    private static final int releaseButton = 7;
    private static final int lowGearButton = 5;
    private static final int highGearButton = 6;
    private static final int intakeOnButton = 1;
    private static final int intakeOffButton = 2;
    private static final int climbOnButton = 3;
    private static final int climbOffButton = 4;
  }

  private final int joystickPort = 0; // joystick usb port on the driver station

  public Joystick m_joystick = new Joystick(joystickPort);

  private JoystickButton m_shootButton = new JoystickButton(m_joystick, Button.shootButton);
  private JoystickButton m_release = new JoystickButton(m_joystick, Button.releaseButton);
  private JoystickButton m_lowGearButton = new JoystickButton(m_joystick, Button.lowGearButton);
  private JoystickButton m_highGearButton = new JoystickButton(m_joystick, Button.highGearButton);
  private JoystickButton m_intakeOnButton = new JoystickButton(m_joystick, Button.intakeOnButton);
  private JoystickButton m_intakeOffButton = new JoystickButton(m_joystick, Button.intakeOffButton);
  private JoystickButton m_climbOnButton = new JoystickButton(m_joystick, Button.climbOnButton);
  private JoystickButton m_climbOffButton = new JoystickButton(m_joystick, Button.climbOffButton);

  private Compressor m_compressor = new Compressor(1, PneumaticsModuleType.CTREPCM);

  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final Intake m_intake = new Intake();
  private final Climb m_climb = new Climb();

  private final ArcadeDrive m_arcadeDrive = new ArcadeDrive(m_drivetrain, m_joystick);

  private final Command m_shootingSequence = new SequentialCommandGroup(new Launch(m_shooter), new Pause(0.5),
      new Reload(m_shooter));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureButtonBindings();
    m_compressor.enableDigital();

    m_drivetrain.setDefaultCommand(m_arcadeDrive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_release.whenPressed(new Launch(m_shooter));
    m_lowGearButton.whenPressed(m_drivetrain.getLowGear());
    m_highGearButton.whenPressed(m_drivetrain.getHighGear());
    m_shootButton.whenPressed(m_shootingSequence);

    m_intakeOnButton.whenPressed(m_intake.getRun());
    m_intakeOnButton.whenReleased(m_intake.getStop());
    m_intakeOffButton.whenPressed(m_intake.getRev());
    m_intakeOffButton.whenReleased(m_intake.getStop());

    m_climbOnButton.whenPressed(m_climb.getUp());
    m_climbOffButton.whenPressed(m_climb.getDown());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
