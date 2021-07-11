// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants;
import frc.util.OutputSystem;
import frc.util.electronics.motor.SuperTalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;


public class ClimbSystem extends OutputSystem {
  
  SuperTalonFX arm = new SuperTalonFX(Constants.CLIMB_MOTOR_ID, 60, false, true,
  NeutralMode.Brake, Constants.SHOOT_GAINS, TalonFXControlMode.PercentOutput);

  // private DigitalInput magnetSensor = new DigitalInput(Constants.CLIMB_MAGNET_CHANNEL);

  public ClimbSystem() {
    super("climbSystem");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getPosition(){
    return arm.getPosition();
  }

  @Override
  public void setOutput(double output) {
    if (getMagnetMode() && output < 0) output = 0;
    arm.set(ControlMode.PercentOutput, output);
  }

  public boolean getMagnetMode(){
    return false;
    // return !magnetSensor.get();
  }
}
