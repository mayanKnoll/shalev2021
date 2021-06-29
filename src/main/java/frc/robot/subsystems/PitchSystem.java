// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.util.commands.ResetSensorsCommand;
import frc.util.dashboard.SuperSystem;

public class PitchSystem extends SuperSystem {

  TalonSRX pitchMotor = new TalonSRX(Constants.PITCH_MOTOR_CAN_ID);
  DigitalInput magnet = new DigitalInput(Constants.PITCH_MAGNET_CHANNEL);
  public PitchSystem() {
    super("PitchSystem");
  }

  @Override
  public void periodic() {
    getTab().putInDashboard("Pitch Value", getPosition());
  }

  public void setOutput(double output){
    pitchMotor.set(ControlMode.PercentOutput, output);
  }

  public double getPosition(){
    return pitchMotor.getSensorCollection().getPulseWidthPosition();
  }

  public boolean getMagnetMode(){
    return magnet.get();
  }
}
