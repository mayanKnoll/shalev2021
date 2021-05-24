/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CollectCommandGroup;
import frc.robot.commands.ShootingCommand;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.ShootSystem;


public class RobotButtons {
    // all joysticks:
    public static Joystick driverJoystick;

    public RobotButtons(Joystick j){
        driverJoystick = j;
    }

    // all the triggers:
    Trigger intakeButton = new Trigger(() -> driverJoystick.getRawButton(5));
    Trigger shootingButton = new Trigger(() -> driverJoystick.getRawButton(2));

    public void loadButtons(DriveSystem driveSystem, CollectionSystem collectionSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, ShootSystem shootSystem){
        intakeButton.whileActiveContinuous(new CollectCommandGroup(kickerSystem, cartridgeSystem, collectionSystem));
        shootingButton.whileActiveContinuous(new ShootingCommand(shootSystem, cartridgeSystem, kickerSystem, 3000));

    }
}

