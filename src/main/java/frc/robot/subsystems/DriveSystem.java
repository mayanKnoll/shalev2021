// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;
import frc.util.Pair;
import frc.util.SuperInterface;
import frc.util.commands.ResetSensorsCommand;
import frc.util.dashboard.SuperSystem;

public class DriveSystem extends SuperSystem implements SuperInterface {

  SwerveModule FR_Module, FL_Module, BL_Module, BR_Module;
  SwerveModule modules[];
  private double angelFromCenter = Math.atan((Constants.ROBOT_LENGTH / 2) / (Constants.ROBOT_WIDTH / 2));

  /** Creates a new DriveSystem. */
  public DriveSystem(String name) {
    super(name);
    getTab().addCommandToDashboard("Reset Sensors", new ResetSensorsCommand(this, 0));
    modules = new SwerveModule[Constants.NUMBER_OF_MODULES];
    modules[0] = FR_Module = new SwerveModule(Constants.SWERVE_FR_ANGLE_MOTOR_CHANNEL,
        Constants.SWERVE_FR_WHEEL_MOTOR_CAN_ID, Constants.SWERVE_FR_ANGLE_ENCODER_CHANNEL, Constants.SWERVE_FR_INVERTED,
        Constants.SWERVE_FR_ANGLE_ENCODER_OFFSET, -angelFromCenter);

    modules[1] = FL_Module = new SwerveModule(Constants.SWERVE_FL_ANGLE_MOTOR_CHANNEL,
        Constants.SWERVE_FL_WHEEL_MOTOR_CAN_ID, Constants.SWERVE_FL_ANGLE_ENCODER_CHANNEL, Constants.SWERVE_FL_INVERTED,
        Constants.SWERVE_FL_ANGLE_ENCODER_OFFSET, angelFromCenter);

    modules[2] = BL_Module = new SwerveModule(Constants.SWERVE_BL_ANGLE_MOTOR_CHANNEL,
        Constants.SWERVE_BL_WHEEL_MOTOR_CAN_ID, Constants.SWERVE_BL_ANGLE_ENCODER_CHANNEL, Constants.SWERVE_BL_INVERTED,
        Constants.SWERVE_BL_ANGLE_ENCODER_OFFSET, 180 - angelFromCenter);

    modules[3] = BR_Module = new SwerveModule(Constants.SWERVE_BR_ANGLE_MOTOR_CHANNEL,
        Constants.SWERVE_BR_WHEEL_MOTOR_CAN_ID, Constants.SWERVE_BR_ANGLE_ENCODER_CHANNEL, Constants.SWERVE_BR_INVERTED,
        Constants.SWERVE_BR_ANGLE_ENCODER_OFFSET, -180 + angelFromCenter);

    setDefaultCommand(new DriveCommand(this));
  }

  @Override
  public void periodic() {
    BL_Module.updateCord(RobotContainer.navxSystem);
    // getTab().addCommandToDashboard("resetSensor", new resetSensor(this));
    BR_Module.updateCord(RobotContainer.navxSystem);
    FL_Module.updateCord(RobotContainer.navxSystem);
    FR_Module.updateCord(RobotContainer.navxSystem);
    getTab().putInDashboard("center x: ", getCenterRobot().getFirst());
    getTab().putInDashboard("center y: ", getCenterRobot().getSecond());
    getTab().putInDashboard("FL ANGLE: ", FL_Module.getEncoderAngle());
    getTab().putInDashboard("FR ANGLE: ", FR_Module.getEncoderAngle());
    getTab().putInDashboard("BL ANGLE: ", BL_Module.getEncoderAngle());
    getTab().putInDashboard("BR ANGLE: ", BR_Module.getEncoderAngle());
    getTab().putInDashboard("FL drive: ", FL_Module.getEncoderDistance());
    getTab().putInDashboard("FR drive: ", FR_Module.getEncoderDistance());
    getTab().putInDashboard("BL drive: ", BL_Module.getEncoderDistance());
    getTab().putInDashboard("BR drive: ", BR_Module.getEncoderDistance());
    int i = 1;
    for (SwerveModule module : modules) {
      getTab().putInDashboard("error " + i + ": ", module.getError());
      i++;
    }
    // System.out.println("center x: " + getCenterRobot().getFirst());
    // System.out.println("center x: " + getCenterRobot().getFirst());
    // This method will be called once per scheduler run
  }

  public Pair<Double, Double> getCenterRobot() {
    double x = 0, y = 0;
    for (SwerveModule module : modules) {
      x += module.getCenter(RobotContainer.navxSystem).getFirst();
      y += module.getCenter(RobotContainer.navxSystem).getSecond();
    }
    return new Pair<Double, Double>(x / Constants.NUMBER_OF_MODULES, y / Constants.NUMBER_OF_MODULES);
  }

  public void fieldOrientedDrive(double x, double y, double z) {
    double angleRad = Math.toRadians(RobotContainer.navxSystem.getAngle360());
    double temp = x * Math.cos(angleRad) + y * Math.sin(angleRad);
    y = -x * Math.sin(angleRad) + y * Math.cos(angleRad);
    x = temp;

    drive(x, y, z);
  }

  public void drive(double x, double y, double z) {
    double R = Math
        .sqrt(Constants.ROBOT_LENGTH * Constants.ROBOT_LENGTH + Constants.ROBOT_WIDTH * Constants.ROBOT_WIDTH);

    double a = x - (z * (Constants.ROBOT_LENGTH / R));
    double b = x + (z * (Constants.ROBOT_LENGTH / R));
    double c = y - (z * (Constants.ROBOT_WIDTH / R));
    double d = y + (z * (Constants.ROBOT_WIDTH / R));

    double frAngle = Math.toDegrees(Math.atan2(b, c)) + 180;
    double flAngle = Math.toDegrees(Math.atan2(b, d)) + 180;
    double blAngle = Math.toDegrees(Math.atan2(a, d)) + 180;
    double brAngle = Math.toDegrees(Math.atan2(a, c)) + 180;

    double frSpeed = Math.sqrt(b * b + c * c);
    double flSpeed = Math.sqrt(b * b + d * d);
    double blSpeed = Math.sqrt(a * a + d * d);
    double brSpeed = Math.sqrt(a * a + c * c);

    // System.out.println(frAngle);

    FR_Module.drive(frAngle, frSpeed);
    FL_Module.drive(flAngle, flSpeed);
    BL_Module.drive(blAngle, blSpeed);
    BR_Module.drive(brAngle, brSpeed);
  }

  public void setMotorsOutput(double speed) {
    for (SwerveModule module : modules)
      module.setWheelMotorOutput(speed);
    ;
  }

  public void setAngleMotorsOutput(double speed) {
    for (SwerveModule module : modules)
      module.setAngleMotorSpeed(speed);
    ;
  }

  @Override
  public void resetSensors(double pos) {
    for (SwerveModule module : modules) {
      module.resetSensor();
    }
  }
}
