// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;

public class CollectCommand extends CommandBase {

  CollectionSystem collectionSystem;
  CartridgeSystem cartridgeSystem;
  int direction;

  double duration;
  int stage;
  Timer timer;

  public CollectCommand(CollectionSystem collectionSystem, CartridgeSystem cartridgeSystem, int direction) {
    addRequirements(collectionSystem, cartridgeSystem);
    this.cartridgeSystem = cartridgeSystem;
    this.collectionSystem = collectionSystem;
    this.direction = direction;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    duration = 0;
    stage = 0;
    timer = new Timer();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (cartridgeSystem.getSwitch()) {
      stage = 1;
      timer.reset();
    } else if (stage == 1){
      if(timer.get() > 0.5) {
        stage = 0;
      }
    }
    
    if(stage == 1) cartridgeSystem.setOutput(Constants.CARTRIDGE_SPEED * direction);
    collectionSystem.setOutput(Constants.COLLECT_SPEED * direction);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    cartridgeSystem.setOutput(0);
    collectionSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
