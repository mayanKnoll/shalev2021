/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.vision.Limelight;

public class AutoChooser {
    private Command autoCommand = null;
    SendableChooser<Command> autoChooser = new SendableChooser<>();

    public AutoChooser(DriveSystem driveSystem, PitchSystem pitchSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, ShootSystem shootSystem, CollectionSystem collectSystem, Limelight limelight) {
        autoChooser.setDefaultOption("turn left",
                new LeftAutoCommand(shootSystem, collectSystem, driveSystem, limelight, cartridgeSystem, kickerSystem, pitchSystem));
        autoChooser.setDefaultOption("turn right",
                new RightAutoCommand(shootSystem,collectSystem, driveSystem, limelight, cartridgeSystem, kickerSystem, pitchSystem));
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    public Command getAutoCommand() {
        return autoChooser.getSelected();
    }

    public void startAuto() {
        autoCommand = getAutoCommand();
        if (autoCommand != null) {
            autoCommand.schedule();
        }
    }

    public void stopAuto() {
        if (autoCommand != null) {
            autoCommand.cancel();
        }
    }
}
