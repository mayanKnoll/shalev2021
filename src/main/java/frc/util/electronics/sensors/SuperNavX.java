package frc.util.electronics.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import frc.util.dashboard.SuperSystem;

public class SuperNavX extends SuperSystem{
  private AHRS navX = new AHRS(SPI.Port.kMXP);

  public SuperNavX(String name) {
    super(name);
    this.resetNavx();
    // getTab().addCommandToDashboard("ResetSensor", new ResetSensorsCommand(this, 0));
  }

  @Override
  public void subsystemPeriodic() {
    getTab().putInDashboard("NavX Angle", getAngle(), 2, 0);
    getTab().putInDashboard("NavX Pitch", getPitch(), 2, 1);
    getTab().putInDashboard("NavX Roll", getRoll(), 2, 2);
    getTab().putInDashboard("NavX Yaw", getYaw(), 2, 3);

  }

  public float getYaw() {
    return navX.getYaw();
  }

  public float getRoll() {
    return navX.getRoll();
  }

  public float getPitch() {
    return navX.getPitch();
  }

  public double getAngle() {
    return -navX.getAngle();
  }

  public double getAngle360() {
    double angle = getAngle() % 360;

    if (angle < 0)
      angle += 360;

    return angle;
  }

  public AHRS getNavX() {
    return navX;
  }

  public void resetNavx() {
    navX.reset();
  }

  public double getRate() {
    return navX.getRate();
  }
}