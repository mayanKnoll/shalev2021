package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.util.OutputSystem;

public class SetOutputCommand extends CommandBase {

  OutputSystem outputSystem;
  double output;
  // BooleanSupplier end;
  public SetOutputCommand(OutputSystem outputSystem, double output) {
    this.outputSystem = outputSystem;
    this.output = output;
    // end = () -> false;
    addRequirements(outputSystem);
  }
  
  // public SetOutputCommand(OutputSystem outputSystem, double output, BooleanSupplier booleanSupplier) {
    // this.outputSystem = outputSystem;
    // this.output = output;
    // end = booleanSupplier;
    // addRequirements(outputSystem);
  // }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     outputSystem.setOutput(output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    outputSystem.setOutput(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return end.getAsBoolean();
    return false;
  }
}
