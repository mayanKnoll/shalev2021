// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.I2C;

//import com.revrobotics.Rev2mDistanceSensor;
//import com.revrobotics.Rev2mDistanceSensor.Port;
//import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.I2C.Port;
import frc.robot.Constants;
import frc.util.OutputSystem;
import frc.util.electronics.sensors.ColorSensor;

public class CartridgeSystem extends OutputSystem{
  
  VictorSP cartMotor = new VictorSP(Constants.CARTRIDGE_MOTOR_ID);
  // AnalogInput distanceSensor = new AnalogInput(3);
  ColorSensor distanceSensor = new ColorSensor(Port.kMXP);


  public CartridgeSystem() {
    super("Cartridge System");
  }
  public double getDistance(){
    return distanceSensor.status();//distanceSensor.getRange(Unit.kMillimeters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println("Distance : " + getDistance());
  }

  @Override
  public void setOutput(double output){
    cartMotor.set(output);
  }
}
