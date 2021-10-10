// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.Constants;
import frc.util.OutputSystem;

public class CartridgeSystem extends OutputSystem{
  
  VictorSP cartMotor = new VictorSP(Constants.CARTRIDGE_MOTOR_ID);
  // AnalogInput distanceSensor = new AnalogInput(3);
  DigitalInput cartageSwitch = new DigitalInput(Constants.CARTAGE_SWITCH_CHANNEL);


  public CartridgeSystem() {
    super("Cartridge System");

  }

  @Override
  public void periodic() {
    getTab().putInDashboard("switch", getSwitch());
    // This method will be called once per scheduler run
    //System.out.println("Distance : " + getDistance());
  }

  public boolean getSwitch(){
    return !cartageSwitch.get();
  }

  @Override
  public void setOutput(double output){
    cartMotor.set(output);
  }
}
