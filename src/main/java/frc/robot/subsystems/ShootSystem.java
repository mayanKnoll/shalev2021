
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import frc.robot.Constants;
import frc.util.dashboard.SuperSystem;
import frc.util.electronics.motor.SuperTalonFX;

public class ShootSystem extends SuperSystem {
  /** Creates a new ShootSystem. */

  SuperTalonFX masterMotor = new SuperTalonFX(Constants.SHOOT_MOTOR_MASTER_ID, 60, false, true,
           NeutralMode.Coast, Constants.SHOOT_GAINS, TalonFXControlMode.Velocity);
  SuperTalonFX slaveMotor = new SuperTalonFX(masterMotor, Constants.SHOOT_MOTOR_SLAVE_ID, 60, true);

  public ShootSystem() {
    super("ShootSystem");
  }

  @Override
  public void periodic() {
  }

  public void setVelocity(double velocity){
    masterMotor.setOutput(velocity);
  }

  public double getVelocity(){
    return masterMotor.getVelocity();
  }

}
