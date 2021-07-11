
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PitchSystem;

public class Pitch_go_to_position extends CommandBase {

    PitchSystem pitchSystem;
    double position;

  public Pitch_go_to_position(PitchSystem pitchSystem, double position) {
    addRequirements(pitchSystem);
    this.pitchSystem = pitchSystem;
    this.position = position;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (position >= 0)
      pitchSystem.setPosition(position);
    else
      pitchSystem.goToMagnet();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pitchSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (this.position >= 0)
      return (Math.abs(pitchSystem.getPosition() - this.position) < 1);
    
    return (pitchSystem.getMagnetMode());
  }
    
}
