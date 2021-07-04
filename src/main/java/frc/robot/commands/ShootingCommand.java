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

public class ShootingCommand extends CommandBase {
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  KickerSystem kickerSystem;
  PitchSystem pitchSystem;
  public ShootingCommand(ShootSystem shootSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    this.shootSystem = shootSystem;
    this.cartridgeSystem = cartridgeSystem;
    this.kickerSystem = kickerSystem;
    this.pitchSystem = pitchSystem;
    addRequirements(shootSystem, cartridgeSystem, kickerSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double velocity = (int)shootSystem.getTab().getFromDashboard("Shoot Velocity", 0);
    // double velocity = RobotContainer.limelight.getY() * 2345;
    //double high = RobotContainer.limelight.getY() * 2345;
    shootSystem.setOutput(velocity);
    //pitchSystem.setOutput(high);
    // shootSystem.setOutput(shootSystem.getTab().getFromDashboard("Shoot Velocity", 0));
    System.out.println(shootSystem.getVelocity());
    if(shootSystem.getVelocity() >= velocity - 100 /*&& pitchSystem.getPosition() > high - 1 && pitchSystem.getPosition() < high + 1*/){
      kickerSystem.setOutput(Constants.KICKER_SPEED);
      cartridgeSystem.setOutput(Constants.CARTRIDGE_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shootSystem.stop();
    kickerSystem.setOutput(0);
    cartridgeSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
