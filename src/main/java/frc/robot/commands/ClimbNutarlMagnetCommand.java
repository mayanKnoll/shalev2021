// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimbSystem;

public class ClimbNutarlMagnetCommand extends CommandBase {
  /** Creates a new ClimbNutarlMagnetCommand. */
  ClimbSystem climbSystem;
  double output;
  boolean CLIMB_FLAG;
  public ClimbNutarlMagnetCommand(ClimbSystem climbSystem, double output) {
    this.climbSystem = climbSystem;
    this.output = output;
    addRequirements(climbSystem);
    CLIMB_FLAG = false;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbSystem.set(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    CLIMB_FLAG = true;
    climbSystem.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return  CLIMB_FLAG || climbSystem.getSwitchMode();
  }
}
