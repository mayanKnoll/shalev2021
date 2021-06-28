package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;
import frc.util.OutputSystem;

public class KickerSystem extends OutputSystem{
  DigitalInput microSwitch = new DigitalInput(Constants.MICRO_SWITCH_CHANNEL);

  VictorSP kickMotor = new VictorSP(Constants.KICKER_MOTOR_ID);

  public KickerSystem() {
    super("Kicker System");
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("swit;lkjhch", getMicroSwitch());
  }
  public boolean getMicroSwitch(){
    return !microSwitch.get();
  }
  public void setOutput(double output){
    kickMotor.set(output);
  }
}
