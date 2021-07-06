/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.CartridgeSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.KickerSystem;
import frc.robot.subsystems.PitchSystem;
import frc.robot.subsystems.ShootSystem;
import frc.util.vision.Limelight;


public class ShootingCommand extends CommandBase {
  CartridgeSystem cartridgeSystem;
  ShootSystem shootSystem;
  KickerSystem kickerSystem;
  PitchSystem pitchSystem;
  DriveSystem driveSystem;
  Limelight limelight;
  double velocity;
  double high;
  public ShootingCommand(Limelight limelight,DriveSystem driveSystem,ShootSystem shootSystem, CartridgeSystem cartridgeSystem, KickerSystem kickerSystem, PitchSystem pitchSystem) {
    this.limelight = limelight;
    this.driveSystem = driveSystem;
    this.shootSystem = shootSystem;
    this.cartridgeSystem = cartridgeSystem;
    this.kickerSystem = kickerSystem;
    this.pitchSystem = pitchSystem;
    addRequirements(shootSystem, cartridgeSystem, kickerSystem, pitchSystem, driveSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   velocity = (int)shootSystem.getTab().getFromDashboard("Shoot Velocity", 0);
   high = shootSystem.getTab().getFromDashboard("pitch position", 0);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shootSystem.setOutput(velocity);
    pitchSystem.setPosition(high);
    // if(limelight.getX() > 0.2 || limelight.getX() < -0.2){

    //     double z = limelight.getX() * Constants.visionGainsZ.kp;
    //     // System.out.println(z);
    //     int dir = z > 0 ? 1 :-1;
    //     driveSystem.drive(0, 0, Math.min(Math.abs(z), 0.5) * dir);

    // }
    // double velocity = RobotContainer.limelight.getY() * 2345;
    //double high = RobotContainer.limelight.getY() * 2345;
    // else{
    // shootSystem.setOutput(shootSystem.getTab().getFromDashboard("Shoot Velocity", 0));
    //System.out.println(shootSystem.getVelocity());
    //System.out.println(shootSystem.getVelocity());
    if(shootSystem.getVelocity() >= velocity - 100 && pitchSystem.getPosition() > high - 1 && pitchSystem.getPosition() < high + 1){
      kickerSystem.setOutput(Constants.KICKER_SPEED);
      cartridgeSystem.setOutput(Constants.CARTRIDGE_SPEED);
    }
  // }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shootSystem.stop();
    kickerSystem.setOutput(0);
    cartridgeSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
