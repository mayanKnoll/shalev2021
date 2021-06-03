// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.ShootSystem;

public class Cartridges2Shooter extends CommandBase {
  /** Creates a new Cartridges2Shooter. */
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  public Cartridges2Shooter(CartridgeSystem cartridgeSystem, ShootSystem shootSystem) {
    this.cartridgeSystem = cartridgeSystem;
    this.shootSystem = shootSystem;
    addRequirements(cartridgeSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(true  /*shootSystem.getVelocity() >= shootSystem.getVelDesired() - 100*/){
      cartridgeSystem.setOutput(Constants.CARTRIDGE_SPEED);
    }
    // }else{
    //   cartridgeSystem.setOutput(0);
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cartridgeSystem.setOutput(0);
  }
    
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
