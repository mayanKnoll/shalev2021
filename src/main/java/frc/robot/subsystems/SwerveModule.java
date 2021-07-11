package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import frc.robot.Constants;
import frc.util.Pair;
import frc.util.PID.Gains;
import frc.util.PID.PIDController;
import frc.util.electronics.motor.SuperMotor;
import frc.util.electronics.motor.SuperTalonFX;
import frc.util.electronics.motor.SuperVictorSP;
import frc.util.electronics.sensors.SuperNavX;

public class SwerveModule {
    private SuperMotor angleMotor;
    private SuperTalonFX wheelMotor;

    private DutyCycleEncoder angleEncoder;
    private double offset = 0;
    private PIDController PID_Controller;
    private double lastSpeed = 0;
    private double lastEncoder = 0;
    private double x = 0;
    private double y = 0;

    private double angleFromCenter;

    public double getError() {
        return PID_Controller.getError();
    }

    public SwerveModule(/* Boolean isSim, */ int angleMotorChannel, int wheelMotorCanID, int encoderChannel,
            boolean inverted, double offset, double angleFromCenter) {
        angleMotor = new SuperVictorSP(angleMotorChannel);
        /*
         * if (isSim) wheelMotor = new SuperVictorSP(wheelMotorCanID); else
         */
        // wheelMotor = new SuperSparkMax(wheelMotorCanID, MotorType.kBrushless, 60, inverted, IdleMode.kBrake);
        wheelMotor = new SuperTalonFX(wheelMotorCanID, 60, inverted, false, NeutralMode.Coast, new Gains("g",0,0,0), TalonFXControlMode.PercentOutput);
        wheelMotor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 40, 40 , 0.5));

        angleEncoder = new DutyCycleEncoder(encoderChannel);
        angleEncoder.setDistancePerRotation(Constants.DEGREES_PER_ENCODER_ROTATION);

        PID_Controller = new PIDController();
        PID_Controller.setGains(Constants.SWERVE_ANGLE_PID_GAINS);

        this.offset = offset;
        this.angleFromCenter = angleFromCenter;
    }

    public double getAngleFromCenter() {
        return angleFromCenter;
    }

    public double getEncoderAngle() {
        return (angleEncoder.getDistance() - offset);
    }

    public double getAngleDistance() {
        return angleEncoder.getDistance() * Constants.DEGREES_PER_ENCODER_ROTATION;
    }

    public double getEncoderDistance() {
        return wheelMotor.getPosition() * Constants.ENCODER_TO_METER;
    }

    public void setWheelMotorOutput(double speed) {
        // int direction = speed > 0 ? 1 : -1;
        // speed = Math.abs(speed) > Math.abs(lastSpeed) + 0.004 ? lastSpeed + 0.004 * direction : speed; 
        wheelMotor.setOutput(speed);
        //lastSpeed = speed;
    }

    public void setAngleMotorSpeed(double speed) {
        angleMotor.setOutput(speed);
    }

    private void setModuleAngle(double angle) {
        PID_Controller.setTargetPosition(angle);
    }

    public void updateCord(SuperNavX navx) {
        double Encoder = getEncoderDistance() - lastEncoder;
        double Angel = getAngleDistance() + navx.getAngle360();
        double disX = Math.sin(Angel) * Encoder;
        double disY = Math.cos(Angel) * Encoder;
        x += disX;
        y += disY;
        lastEncoder += Encoder;
    }

    public Pair<Double, Double> getCenter(SuperNavX navx) {
        double x = Constants.LEN_MODULE_FROM_CENTER * Math.sin(navx.getAngle360() + angleFromCenter);
        double y = Constants.LEN_MODULE_FROM_CENTER * Math.cos(navx.getAngle360() + angleFromCenter);
        return new Pair<Double, Double>(x + this.x, y + this.y);
    }

    private void goToPositionAngle(double currAngle) {
        angleMotor.setOutput(PID_Controller.getOutput(currAngle));
    }
int i = 0;
    public void drive(double targetAngle, double speed) {
        // System.out.println("t" + targetAngle);

 
        double currAngle = getEncoderAngle();
        //System.out.println("c" + currAngle);
        double currAngleMod = currAngle < 0 ? (currAngle % 360) + 360 : (currAngle % 360);

        currAngleMod += 3211;
        targetAngle += 3211;

        double delta = currAngleMod - targetAngle;

        if (delta > 180) {
            targetAngle += 360;
        } else if (delta < -180) {
            targetAngle -= 360;
        }

        if (Math.abs(currAngleMod - targetAngle) > 90) {
            if (currAngleMod - targetAngle > 0) {
                targetAngle += 180;
            } else {
                targetAngle -= 180;
            }
            speed *= -1;
        }

        targetAngle += currAngle - currAngleMod;

        this.setModuleAngle(targetAngle);
        this.goToPositionAngle(currAngle);
        this.setWheelMotorOutput(speed);
    }

    public void resetSensor() {
        angleEncoder.reset();
        wheelMotor.reset(0);
    }
}