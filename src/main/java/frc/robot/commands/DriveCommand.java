package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  DriveSystem driveSystem;
  double lastX = 0;
  double lastY = 0;
  public DriveCommand(DriveSystem sys) {
    this.driveSystem = sys;
    addRequirements(sys);
  }


 @Override
  public void initialize() {

  }

  @Override
  public void execute() {

    double x = RobotContainer.driveJoystick.getRawAxis(4);
    double y = -RobotContainer.driveJoystick.getRawAxis(5);
    double z = RobotContainer.driveJoystick.getRawAxis(0);
    x = Math.abs(x) > 0.02 ? x : 0;
    y = Math.abs(y) > 0.02 ? y : 0;
    z = Math.abs(z) > 0.02 ? z : 0;

    // x = Math.pow(x, 3);
    // y = Math.pow(y, 3);
    // z = Math.pow(z, 3);

    if(RobotContainer.driveJoystick.getPOV() != -1){
      double targetAngle = RobotContainer.driveJoystick.getPOV();
      double currAngle = RobotContainer.navxSystem.getAngle360();
      
      targetAngle = getBestTargetAngle(currAngle, targetAngle);

      double error = (targetAngle - currAngle);
      double dir = error > 0 ? 1 : -1;
      z = Math.min(Math.abs(error * Constants.SWERVE_ANGLE_PID_GAINS.kp), 0.5) * dir;
    }
    //else if(RobotContainer.driveJoystick.getRawAxis(3) > 0.3){
      x *= 0.5;
      y *= 0.5;
      z *= 0.5;
    //}
    // int direction = x > 0 ? 1 : -1;
    // x = Math.abs(x) > Math.abs(lastX) + 0.008 ? lastX + 0.008 * direction : x;
    // direction = y > 0 ? 1 : -1;
    // y = Math.abs(y) > Math.abs(lastY) + 0.008 ? lastY + 0.008 * direction : y; 
    driveSystem.fieldOrientedDrive(x, y, z);
    // driveSystem.drive(x, y, z);
    lastX = x;
    lastY = y;
  }

  @Override
  public void end(boolean interrupted) {
    driveSystem.setMotorsOutput(0);
    driveSystem.setAngleMotorsOutput(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  public static double getBestTargetAngle(double currAngle, double targetAngle) {
    double currAngleMod = currAngle < 0 ? (currAngle % 360) + 360 : (currAngle % 360);

    currAngleMod += 3211;
    targetAngle += 3211;

    double delta = currAngleMod - targetAngle;

    if (delta > 180) {
        targetAngle += 360;
    } else if (delta < -180) {
        targetAngle -= 360;
    }

    targetAngle += currAngle - currAngleMod;

    return targetAngle;
}
}