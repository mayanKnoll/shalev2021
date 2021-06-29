package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;
import frc.util.OutputSystem;

public class CollectionSystem extends OutputSystem{
  
  VictorSP rightMotor = new VictorSP(Constants.COLLECTION_RIGHT_MOTOR_ID); 
 VictorSP leftMotor = new VictorSP(Constants.COLLECTION_LEFT_MOTOR_ID); 

  public CollectionSystem() {
    super("Collection System");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setOutput(double output){
   leftMotor.set(-output);
    rightMotor.set(output);
  }
}
