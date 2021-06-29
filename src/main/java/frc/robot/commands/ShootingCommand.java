/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.ShootSystem;

public class ShootingCommand extends CommandBase {
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  KickerSystem kickerSystem;
  double velocity;
  public ShootingCommand(ShootSystem shootSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, double velocity) {
    this.shootSystem = shootSystem;
    this.cartridgeSystem = cartridgeSystem;
    this.kickerSystem = kickerSystem;
    this.velocity = velocity;
    addRequirements(shootSystem, cartridgeSystem, kickerSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // velocity = (int)shootSystem.getTab().getFromDashboard("Shoot Velocity", 0);
    shootSystem.setOutput(velocity);

    if(shootSystem.getVelocity() >= velocity - 100){
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
