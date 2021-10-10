// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSystem;

public class KeepClimbCommand extends CommandBase {
  
  ClimbSystem climbSystem;
  PIDController pidController;
  public KeepClimbCommand(ClimbSystem climbSystem) {
    this.climbSystem = climbSystem;
    addRequirements(climbSystem);
    pidController = new PIDController(0.000005, 0, 0);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.setSetpoint(climbSystem.getPosition());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // if(!Constants.CLIMB_FLAG){
      if(!climbSystem.getSwitchMode()) climbSystem.setOutput(pidController.calculate(climbSystem.getPosition()));  
    // }
    // else{ 
      // if(!climbSystem.getSwitchMode()) climbSystem.setOutput(-0.1);
      // else { 
        // Constants.CLIMB_FLAG = false;
        // pidController.setSetpoint(climbSystem.getPosition());
      // }
    // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climbSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
