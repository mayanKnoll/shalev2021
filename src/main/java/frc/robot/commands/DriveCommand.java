// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  DriveSystem driveSystem;

  public DriveCommand(DriveSystem sys) {
    this.driveSystem = sys;
    addRequirements(sys);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double x = RobotContainer.driveJoystick.getRawAxis(4);
    double y = -RobotContainer.driveJoystick.getRawAxis(5);
    double z = RobotContainer.driveJoystick.getRawAxis(0);

    // driveSystem.fieldOrientedDrive(x, y, z);
    driveSystem.drive(x, y, z);
  }

  // Called once the command ends or is interrupted.
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
