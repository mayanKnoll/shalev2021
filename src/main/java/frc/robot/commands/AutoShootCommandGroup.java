// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootCommandGroup extends SequentialCommandGroup {
  /** Creates a new AutoShootCommandGroup. */
  public AutoShootCommandGroup(ShootSystem shootSystem,DriveSystem driveSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    // Add your commands in the addCommands() call, e.g.
    //
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(deadline(new TimeCommand(11), new ShootingCommand(shootSystem, cartridgeSystem, kickerSystem, pitchSystem)),deadline(new TimeCommand(4), new DriveCommand(driveSystem, -1 , 0)));
  }
}
