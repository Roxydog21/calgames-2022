package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends CommandBase {
    private static final class Config {
        public static final int kLeftStickY = 1;
        public static final int kRightStickX = 2;

        public static final double kSpeedMult = 0.7;
        public static final double kTurnMult = 0.8;
    }

    private final Drivetrain m_drivetrain;
    private final Joystick m_joystick;

    public ArcadeDrive(Drivetrain drivetrain, Joystick joystick) {
        m_drivetrain = drivetrain;
        m_joystick = joystick;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double speed = -m_joystick.getRawAxis(Config.kLeftStickY) * Config.kSpeedMult;
        double turn = m_joystick.getRawAxis(Config.kRightStickX) * Config.kTurnMult;

        m_drivetrain.getDiffDrive().arcadeDrive(speed, turn, true);
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.getDiffDrive().arcadeDrive(0.0, 0.0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
