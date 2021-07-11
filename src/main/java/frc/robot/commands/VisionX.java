// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSystem;
import frc.util.vision.Limelight;
import frc.util.vision.Limelight.limelightLEDMode;

public class VisionX extends CommandBase {
  /** Creates a new VisionX. */
  DriveSystem driveSystem;
  Limelight limelight;
  int i = 0;
  PIDController pidController;
  public VisionX(Limelight limelight,DriveSystem driveSystem) {
    this.limelight = limelight;
    pidController = new PIDController(0.02, 0.003, 0.001);
    this.driveSystem = driveSystem;
    addRequirements(driveSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    i = 0;
    Constants.VisionFlag = false;
    pidController.setSetpoint(0);
    // limelight.changeMode(true);
    limelight.setLEDMode(limelightLEDMode.kOn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double goalAngle = limelight.getX();

    double z = pidController.calculate(goalAngle);
    
    int dir = z < 0 ? 1 :-1;
    
    driveSystem.drive(0, 0, Math.min(Math.abs(z), 0.5) * dir);
  
    Constants.VisionFlag = Math.abs(goalAngle) <= 1.5;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Constants.VisionFlag = true;
    driveSystem.drive(0, 0, 0);
    // limelight.changeMode(false);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
