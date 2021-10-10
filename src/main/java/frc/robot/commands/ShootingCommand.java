/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.vision.Limelight.limelightLEDMode;


public class ShootingCommand extends CommandBase {
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  KickerSystem kickerSystem;
  PitchSystem pitchSystem;
  double velocity;
  double high;
  public ShootingCommand(ShootSystem shootSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    this.shootSystem = shootSystem;
    this.cartridgeSystem = cartridgeSystem;
    this.kickerSystem = kickerSystem;
    this.pitchSystem = pitchSystem;
    addRequirements(shootSystem, cartridgeSystem, kickerSystem, pitchSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.limelight.setLEDMode(limelightLEDMode.kOn);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // get height from limelight
    double y = RobotContainer.limelight.getY();

    // calc speed and pitch
    //double velocity = shootSystem.getTab().getFromDashboard("Shoot Velocity", 0);
    double velocity = 7595.8 - 49.144 * (y) + 2.9192 * Math.pow(y, 2) - 800;
    double high = 25.145 - 0.838 * (y) + 0.0349 * Math.pow(y, 2) - 0.0034 * Math.pow(y, 3) + 5;
  

    // shootSystem.setOutput(velocity);
    shootSystem.setVelocity(velocity);
    shootSystem.goToVel();  
    if (high > 100) high = 99;
    pitchSystem.setPosition(high);
    
    System.out.println("flag: " + Constants.VisionFlag + " | vel: " + velocity + " | velt: " + shootSystem.getVelocity()
    + " | high: " + high + " | pos: " + pitchSystem.getPosition());

    //
    if(RobotContainer.copilotJoystick.getRawAxis(3) > 0.05 || (Constants.VisionFlag && Math.abs(shootSystem.getVelocity() - velocity) < 150
      && Math.abs(pitchSystem.getPosition() - high) < 2)){
      kickerSystem.setOutput(Constants.KICKER_SPEED);
      cartridgeSystem.setOutput(Constants.CARTRIDGE_SPEED);
    }
  // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shootSystem.stop();
    kickerSystem.setOutput(0);
    cartridgeSystem.setOutput(0);
    pitchSystem.setOutput(0);

    RobotContainer.limelight.setLEDMode(limelightLEDMode.kOff);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
