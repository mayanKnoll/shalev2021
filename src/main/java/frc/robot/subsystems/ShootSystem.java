
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import frc.robot.Constants;
import frc.robot.commands.SetOutputCommand;
import frc.robot.commands.StopShooterCommand;
import frc.util.OutputSystem;
import frc.util.electronics.motor.SuperTalonFX;

public class ShootSystem extends OutputSystem {
  /** Creates a new ShootSystem. Constants.SHOOT_MOTOR_MASTER_ID*/
  private double velDesired = 0;
  SuperTalonFX masterMotor = new SuperTalonFX(Constants.SHOOT_MOTOR_MASTER_ID, 80, false, true,
           NeutralMode.Coast, Constants.SHOOT_GAINS, TalonFXControlMode.Velocity);
  SuperTalonFX slaveMotor = new SuperTalonFX(masterMotor, Constants.SHOOT_MOTOR_SLAVE_ID, 80, true);



  public ShootSystem() {
    super("ShootSystem");
    setDefaultCommand(new StopShooterCommand(this));
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("vel", getVelocity());
  }

  public void setOutput(double velocity){
    masterMotor.setMode(TalonFXControlMode.Velocity);
    masterMotor.setOutput(velocity);
  }

  
  public void stop(){
    masterMotor.set(TalonFXControlMode.PercentOutput, 0);
  }

  public double getVelocity(){
    return masterMotor.getVelocity();
  }

  public double getVelDesired(){
    return velDesired;
  }

}
