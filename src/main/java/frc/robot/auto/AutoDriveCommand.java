// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class AutoDriveCommand extends CommandBase {
  DriveSystem driveSystem;
  double x = 0;
  double y = 0;
  public AutoDriveCommand(DriveSystem sys, double x,double y) {
    this.x = x;
    this.y = y;
    this.driveSystem = sys;
    addRequirements(sys);
  }


 @Override
  public void initialize() {

  }

  @Override
  public void execute() {



    // driveSystem.fieldOrientedDrive(x, y, 0);
    driveSystem.drive(x, y, 0);
  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.setMotorsOutput(0);
    driveSystem.setAngleMotorsOutput(0);
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
