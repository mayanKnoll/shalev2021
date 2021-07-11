// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoShootCommandGroup;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.ClimbSystem;
import frc.robot.subsystems.CollectionSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.electronics.sensors.SuperNavX;
import frc.util.vision.Limelight;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static final Joystick driveJoystick = new Joystick(0);
  public static final Joystick copilotJoystick = new Joystick(1);
  public static final SuperNavX navxSystem = new SuperNavX("Navx");
  public static final Limelight limelight = new Limelight.Builder().setPitchAngle(56).setHigh(1.64).build();


  // all subsystems
  public final DriveSystem driveSystem = new DriveSystem("DriveSystem");
  public final CollectionSystem collectionSystem = new CollectionSystem();
  public final ClimbSystem climbSystem = new ClimbSystem();
  public final CartridgeSystem cartridgeSystem = new CartridgeSystem();
  public final KickerSystem kickerSystem = new KickerSystem();
  public final ShootSystem shootSystem = new ShootSystem();
  public final PitchSystem pitchSystem = new PitchSystem();

  public final RobotButtons buttons = new RobotButtons(driveJoystick, copilotJoystick);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    buttons.loadButtons(driveSystem, climbSystem, collectionSystem, cartridgeSystem, kickerSystem, shootSystem, limelight, navxSystem, pitchSystem);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoShootCommandGroup(shootSystem, driveSystem, cartridgeSystem, kickerSystem, pitchSystem);
  }
}
