// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.util.OutputSystem;
import frc.util.dashboard.PID_VA;
import frc.util.electronics.motor.SuperTalonSRX;

public class PitchSystem extends OutputSystem {

  // private SuperTalonSRX pitchMotor = new SuperTalonSRX(Constants.PITCH_MOTOR_CAN_ID, 60, true, false, 0, 0.0146, 1, Constants.PITCH_GAINS, ControlMode.Position);
  private SuperTalonSRX pitchMotor = new SuperTalonSRX(Constants.PITCH_MOTOR_CAN_ID, 60, true
  , false, 0, 1, 1, Constants.PITCH_GAINS, ControlMode.Position);
  // private TalonSRX pitchMotor = new TalonSRX(Constants.PITCH_MOTOR_CAN_ID);
  private DigitalInput magnet = new DigitalInput(Constants.PITCH_MAGNET_CHANNEL);
  double minPos = 0, maxPos = 100;
  
  public PitchSystem() {
    super("PitchSystem");
    PID_VA pid = new PID_VA("pitch", getTab(), Constants.PITCH_GAINS, 1, 2);
    // setDefaultCommand(new SetOutputCommand(this, 20));
  }

  
  @Override
  public void periodic() {
    //getTab().putInDashboard("Pitch Value", getPosition());
    getTab().putInDashboard("Pitch Magnet", getMagnetMode());
    getTab().putInDashboard("potion", getPosition());
    
    if(getMagnetMode()) pitchMotor.reset(0);
  }

  public void setOutput(double output){
    // double out = output > maxPos ? maxPos : output < minPos ? minPos : output;
    pitchMotor.set(ControlMode.PercentOutput, output);
    }

  public double getPosition(){
    return pitchMotor.getPosition() * 0.0146;
  }

  public boolean getMagnetMode(){
    return !magnet.get();
  }

  public void set(double output) {
      output = Math.abs(output) > 0.7 ? output > 0 ? 0.7: -0.7 :output;
      pitchMotor.set(ControlMode.PercentOutput, output);
        
  }

  public void setPosition(double position){
    // position = position / 0.0146;
    //System.out.println(position);
    position = position > maxPos ? maxPos : position < minPos ? minPos : position;

    double pos = getPosition();
    set((position - pos) * 0.05);
  }

  public void goToMagnet(){
    if (!getMagnetMode())
      setOutput(-0.3);
  }

}

