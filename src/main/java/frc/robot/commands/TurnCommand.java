// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.util.vision.Limelight;
import frc.util.vision.Limelight.limelightLEDMode;

public class TurnCommand extends CommandBase {
  DriveSystem driveSystem;
  Limelight limelight;
  double z;
  public TurnCommand(DriveSystem driveSystem, Limelight limelight, double z) {
    this.driveSystem = driveSystem;
    this.limelight = limelight;
    this.z = z;
    addRequirements(driveSystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(limelightLEDMode.kOn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSystem.drive(0, 0, z);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    limelight.setLEDMode(limelightLEDMode.kOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return limelight.isValid();
  }
}
