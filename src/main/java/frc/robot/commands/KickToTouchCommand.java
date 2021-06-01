
package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.KickerSystem;

public class KickToTouchCommand extends CommandBase {

  DigitalInput microSwitch = new DigitalInput(Constants.MICRO_SWITCH_CHANNEL);

  KickerSystem kickerSystem;
  double output;

  public KickToTouchCommand(KickerSystem kickerSystem, double output) {
    addRequirements(kickerSystem);
    this.kickerSystem = kickerSystem;

    this.output = output;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!microSwitch.get()){
      kickerSystem.setOutput(output);
    } else {
      System.out.println("---------------------");
      System.out.println(kickerSystem.toString());
      kickerSystem.setOutput(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    kickerSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
