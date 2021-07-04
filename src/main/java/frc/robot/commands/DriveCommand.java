package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

public class DriveCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  DriveSystem driveSystem;

  public DriveCommand(DriveSystem sys) {
    this.driveSystem = sys;
    addRequirements(sys);
  }

 @Override
  public void initialize() {

  }

  @Override
  public void execute() {

    double x = RobotContainer.driveJoystick.getRawAxis(0);
    double y = -RobotContainer.driveJoystick.getRawAxis(1) ;
    double z = RobotContainer.driveJoystick.getRawAxis(4);
    x = Math.abs(x) > 0.02 ? x : 0;
    y = Math.abs(y) > 0.02 ? y : 0;
    z = Math.abs(z) > 0.02 ? z : 0;
    x = Math.pow(x, 3);
    y = Math.pow(y, 3);

    if(RobotContainer.driveJoystick.getPOV() != -1){
      z = RobotContainer.driveJoystick.getPOV();
      double angle = RobotContainer.navxSystem.getAngle360();
      z = (z - angle) * Constants.SWERVE_ANGLE_PID_GAINS.kp;
    }
    else {
      z = Math.pow(z, 3);
    }

    driveSystem.fieldOrientedDrive(x, y, z);
    // driveSystem.drive(x, y, z);
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
}