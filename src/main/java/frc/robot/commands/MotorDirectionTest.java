package frc.robot.commands;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotButtons;
import frc.robot.subsystems.PitchSystem;

public class MotorDirectionTest extends CommandBase {

  PitchSystem pitchSystem;
  static double position;

  public MotorDirectionTest(PitchSystem pitchSystem, double p) {
    this.pitchSystem = pitchSystem;
    MotorDirectionTest.position = 90;
    addRequirements(pitchSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    position *= -1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pitchSystem.setPosition(pitchSystem.getTab().getFromDashboard("pitch position", 0));
    //System.out.println("Position  : " + position);
    //pitchSystem.set(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pitchSystem.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
