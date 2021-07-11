
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.Constants;
import frc.robot.commands.StopShooterCommand;
import frc.util.OutputSystem;
import frc.util.electronics.motor.SuperTalonFX;

public class ShootSystem extends OutputSystem {
  /** Creates a new ShootSystem. Constants.SHOOT_MOTOR_MASTER_ID*/
  private double velDesired = 0;
  SuperTalonFX masterMotor = new SuperTalonFX(Constants.SHOOT_MOTOR_MASTER_ID, 60, false, true,
           NeutralMode.Coast, Constants.SHOOT_GAINS, TalonFXControlMode.PercentOutput);
  SuperTalonFX slaveMotor = new SuperTalonFX(masterMotor, Constants.SHOOT_MOTOR_SLAVE_ID, 60, true);
  // SuperTalonFX slaveMotor = new SuperTalonFX(Constants.SHOOT_MOTOR_SLAVE_ID, 60, true, true,
  // NeutralMode.Coast, Constants.SHOOT_GAINS, TalonFXControlMode.Velocity);

  PIDController pidControl;

  public ShootSystem() {
    super("ShootSystem");
    setDefaultCommand(new StopShooterCommand(this));
    pidControl = new PIDController(0.45, 0.002, 0.03);
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("vel", getVelocity());
  }

  // public void setOutput(double velocity){
  //   masterMotor.setMode(TalonFXControlMode.Velocity);
  //   masterMotor.setOutput(velocity);
  // }

  public void setOutput(double output){
    masterMotor.setMode(TalonFXControlMode.PercentOutput);
    if (output < 0) output = 0;
    masterMotor.setOutput(output);
  }

  public void setVelocity(double vel) {
   pidControl.setSetpoint(vel); 
  }

  public void goToVel(){
    setOutput(pidControl.calculate(getVelocity()));
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

  public ControlMode getMode() {
    return masterMotor.getControlMode();
  } 

}
