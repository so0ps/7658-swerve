// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;
import java.lang.Math.*;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.sensors.WPI_Pigeon2;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;

public class Balance extends CommandBase {
  /** Creates a new DriveForward. */

  private double speed;
  private final DrivetrainSubsystem m_drivetrainSubsystem = RobotContainer.m_drivetrainSubsystem;
  private final WPI_Pigeon2 m_pigeon = new WPI_Pigeon2(Constants.DRIVETRAIN_PIGEON_ID);

  // If the direction is 0, move forwards. If it is 1, move sideways
  public Balance() {
    speed = Constants.BALANCE_SPEED;
    addRequirements(m_drivetrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
    // Set the correct motors to move in the right direction
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println(m_pigeon.getPitch());
    if (m_pigeon.getPitch() < 0)
    {
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
        m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        speed * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
                        0,
                        0,
                        m_drivetrainSubsystem.getGyroscopeRotation()
                )
        );
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("ENDS WORKS");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(m_pigeon.getPitch()) < 2.0);
  }
}
