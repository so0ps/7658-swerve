// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.Auton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Motors;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Arm;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();

  private final XboxController m_controller = new XboxController(Constants.JOYSTICK.XBOX_PORT);

  public final static Motors m_motors = new Motors();

  public static ButtonStation m_station = new ButtonStation();

  public final Pneumatics m_pneumatics = new Pneumatics();

  public final Arm m_arm = new Arm();

  private final Auton m_auton = new Auton();


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_controller.getLeftX()) * -DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_controller.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_controller.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Back button zeros the gyroscope
    new Button(m_controller::getBackButton)
            // No requirements because we don't need to interrupt anything
            .whenPressed(m_drivetrainSubsystem::zeroGyroscope);

    // Set the arm controls
    m_station.redOne.whileTrue(m_arm.raise());
    m_station.redTwo.whileTrue(m_arm.lower());
    m_station.redOne.onFalse(m_arm.stopBase());
    m_station.redTwo.onFalse(m_arm.stopBase());
    m_station.greenOne.whileTrue(m_arm.pivotForward());
    m_station.greenTwo.whileTrue(m_arm.pivotBackward());
    m_station.greenOne.onFalse(m_arm.stopPivot());
    m_station.greenTwo.onFalse(m_arm.stopPivot());

    // Set the claw controls
    m_station.blueOne.onTrue(m_pneumatics.clawOpen());
    m_station.blueTwo.onTrue(m_pneumatics.clawClose());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_auton;
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.1);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}
