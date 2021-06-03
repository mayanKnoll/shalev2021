
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import frc.robot.Constants;
import frc.util.OutputSystem;
import frc.util.electronics.motor.SuperTalonFX;

public class ShootSystem extends OutputSystem {
  /** Creates a new ShootSystem. */
  private double velDesired = 0;
  SuperTalonFX masterMotor = new SuperTalonFX(Constants.SHOOT_MOTOR_MASTER_ID, 60, false, true,
           NeutralMode.Coast, Constants.SHOOT_GAINS, TalonFXControlMode.Velocity);
  SuperTalonFX slaveMotor = new SuperTalonFX(masterMotor, Constants.SHOOT_MOTOR_SLAVE_ID, 60, true);



  public ShootSystem() {
    super("ShootSystem");

    
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("vel", getVelocity());
  }

  public void setOutput(double velocity){
    velDesired = velocity;
    masterMotor.setOutput(velocity);
  }

  public double getVelocity(){
    return masterMotor.getVelocity();
  }

  public double getVelDesired(){
    return velDesired;
  }

}
