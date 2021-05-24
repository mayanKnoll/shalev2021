package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;
import frc.util.OutputSystem;

public class KickerSystem extends OutputSystem{

  VictorSP kickMotor = new VictorSP(Constants.KICKER_MOTOR_ID);

  public KickerSystem() {
    super("Kicker System");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setOutput(double output){
    kickMotor.set(output);
  }
}