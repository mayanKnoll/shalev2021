// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
import frc.util.PID.Gains;
import frc.util.PID.PIDController;
import frc.util.electronics.sensors.SuperNavX;
import frc.util.vision.Limelight;
import frc.util.vision.Limelight.limelightCameraMode;
import frc.util.vision.Limelight.limelightLEDMode;

public class DriveVisionCommand extends CommandBase {

  private DriveSystem driveSystem;
  private Limelight limelight;
  private PIDController xController, zController, yController;
  private SuperNavX navX;

  public DriveVisionCommand(DriveSystem driveSystem, Limelight limelight, SuperNavX navX, Gains gainsX, Gains gainsY, Gains gainsZ, double maxSpeed, double posZ, double posX, double posY) {
    this.driveSystem = driveSystem;
    this.limelight = limelight;
    this.navX = navX;
    this.xController = new PIDController(gainsX, posX, maxSpeed);
    this.zController = new PIDController(gainsZ, posZ, maxSpeed);
    this.yController = new PIDController(gainsY, posY, maxSpeed);

    addRequirements(driveSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setCameraMode(limelightCameraMode.kVision);
    limelight.setLEDMode(limelightLEDMode.kOn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSystem.drive(
        xController.getOutput(limelight.getX()),
        yController.getOutput(limelight.getY()),
        zController.getOutput(limelight.getAngleToTarget(navX.getAngle()))
    );
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSystem.setMotorsOutput(0);
    driveSystem.setAngleMotorsOutput(0);

    limelight.setCameraMode(limelightCameraMode.kView);
    limelight.setLEDMode(limelightLEDMode.kOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
