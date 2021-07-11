// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.vision.Limelight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootingCommandGroup extends ParallelCommandGroup {
  /** Creates a new intakeBall2ShooterCommand. */
  public ShootingCommandGroup(Limelight limelight,DriveSystem driveSystem, KickerSystem kickerSystem,
   CartridgeSystem cartridgeSystem, ShootSystem shootSystem, PitchSystem pitchSystem) {
    addCommands(new VisionX(limelight, driveSystem), new ShootingCommand(shootSystem, cartridgeSystem, kickerSystem, pitchSystem));
    
  }
}
