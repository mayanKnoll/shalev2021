/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.CollectCommand;
import frc.robot.commands.DriveVisionCommand;
import frc.robot.commands.Pitch_go_to_position;
import frc.robot.commands.SetOutputCommand;
import frc.robot.commands.ShootFasterCommand;
import frc.robot.commands.ShootingCommandGroup;
import frc.robot.commands.CloseShootingCommand;
import frc.robot.commands.climbPositionCommand;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.ClimbSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.electronics.sensors.SuperNavX;
import frc.util.vision.Limelight;


public class RobotButtons {
    // all joysticks:
    public static Joystick driverJoystick;
    public static Joystick copilotJoystick;

    public RobotButtons(Joystick j, Joystick c){
        driverJoystick = j;
        copilotJoystick = c;
    }

    // all the triggers:
    Trigger intakeButton = new Trigger(() -> copilotJoystick.getPOV() == 270);
    Trigger emissionButton = new Trigger(() -> copilotJoystick.getRawButton(1));
    Trigger climbPosition = new Trigger(() -> copilotJoystick.getRawButton(7));
    Trigger climbUp = new Trigger(() -> copilotJoystick.getRawButton(6));
    Trigger climbDown = new Trigger(() -> copilotJoystick.getRawButton(5));
    Trigger fasterShooter = new Trigger(() -> copilotJoystick.getRawButton(3));
    Trigger closeShootTrigger = new Trigger(() -> copilotJoystick.getRawButton(3));
    Trigger cartridgeButton = new Trigger(() -> copilotJoystick.getRawButton(4));
    Trigger cartridPlitaButton = new Trigger(() -> copilotJoystick.getRawButton(2));
    Trigger shootingButton = new Trigger(() -> copilotJoystick.getPOV() == 90);

    //Trigger testButton = new Trigger(() -> driverJoystick.getRawButton(X));
    Trigger lowSpeedButton = new Trigger(() -> driverJoystick.getRawAxis(2) > 0);
    Trigger visionButton = new Trigger(() -> driverJoystick.getRawButton(3));
    Trigger pitchTestButton = new Trigger(() -> driverJoystick.getRawButton(1));

    public static int position = 100;

    public void loadButtons(DriveSystem driveSystem, ClimbSystem climbSystem ,CollectionSystem collectionSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, ShootSystem shootSystem, Limelight limelight, SuperNavX navX, PitchSystem pitchSystem){
        intakeButton.whileActiveContinuous(new CollectCommand(collectionSystem, cartridgeSystem, 1));
        emissionButton.whileActiveContinuous(new CollectCommand(collectionSystem, cartridgeSystem, -1));
        cartridgeButton.whileActiveContinuous(new SetOutputCommand(cartridgeSystem, Constants.CARTRIDGE_SPEED));
        cartridPlitaButton.whileActiveContinuous(new SetOutputCommand(cartridgeSystem, -(Constants.CARTRIDGE_SPEED)));
        shootingButton.whileActiveContinuous(new ShootingCommandGroup(limelight, driveSystem,
         kickerSystem, cartridgeSystem, shootSystem , pitchSystem));
        shootingButton.whenInactive(new Pitch_go_to_position(pitchSystem, -100));
        climbPosition.whileActiveContinuous(new climbPositionCommand(climbSystem));
        //if(Constants.)
        fasterShooter.whileActiveContinuous(new ShootFasterCommand(shootSystem));
        climbDown.whileActiveContinuous(new SetOutputCommand(climbSystem, 1));
        climbUp.whileActiveContinuous(new SetOutputCommand(climbSystem, -1));
        // pitchTestButton.whileActiveContinuous(new MotorDirectionTest(pitchSystem, position));
        pitchTestButton.whileActiveContinuous(new SetOutputCommand(pitchSystem, -0.5));
        // testButton.whileActiveContinuous(new ShootingCommandGroup(kickerSystem, cartridgeSystem, shootSystem, 2000));
        visionButton.whileActiveContinuous(new DriveVisionCommand(driveSystem, limelight, navX, Constants.visionGainsX , Constants.visionGainsY, Constants.visionGainsZ ,0.8, 0, 0 , 0));
        closeShootTrigger.whenActive(new CloseShootingCommand(shootSystem, cartridgeSystem, kickerSystem, pitchSystem));
    }
}

