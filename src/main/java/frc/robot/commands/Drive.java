// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;

public class Drive extends CommandBase {
  /** Creates a new DriveForward. */

  private double time;
  private double speed;
  private final DrivetrainSubsystem m_drivetrainSubsystem = RobotContainer.m_drivetrainSubsystem;
  private int direction;
  private Timer timer = new Timer();

  // If the direction is 0, move forwards. If it is 1, move sideways
  public Drive(double theSpeed, double theTime, int theDirection) {
    // Use addRequirements() here to declare subsystem dependencies.
    speed = theSpeed;
    time = theTime;
    direction = theDirection;
    addRequirements(m_drivetrainSubsystem);
    // Set the correct motors to move in the right direction
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (direction == 0)
    {
      //System.out.println("MOVE SIDEWAYS");
      m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        -speed * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                        0,
                        0,
                        m_drivetrainSubsystem.getGyroscopeRotation()
                )
        );
    }
    else
    {
      //System.out.println("MOVE FORWARDS");
      m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        0,
                        -speed * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                        0,
                        m_drivetrainSubsystem.getGyroscopeRotation()
                )
        );
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    timer.stop();
    //System.out.println("END");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (timer.hasElapsed(time));
  }
}
