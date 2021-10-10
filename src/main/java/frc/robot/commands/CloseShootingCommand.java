// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;

public class CloseShootingCommand extends CommandBase {
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  KickerSystem kickerSystem;
  PitchSystem pitchSystem;

  int velocity = 6000;
  int high = 1;
  public CloseShootingCommand(ShootSystem shootSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    this.shootSystem = shootSystem;
    this.cartridgeSystem = cartridgeSystem;
    this.kickerSystem = kickerSystem;
    this.pitchSystem = pitchSystem;
    addRequirements(shootSystem, cartridgeSystem, kickerSystem, pitchSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shootSystem.setVelocity(velocity);
    shootSystem.goToVel();
    pitchSystem.setPosition(high);

    if(Math.abs(shootSystem.getVelocity() - velocity) < 150){
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
    pitchSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
