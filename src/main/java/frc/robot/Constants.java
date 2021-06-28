package frc.robot;

import frc.util.PID.Gains;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double ROBOT_LENGTH = 0.61;
    public static final double ROBOT_WIDTH = 0.61;

    public static final int SWERVE_FR_WHEEL_MOTOR_CAN_ID = 3;
    public static final int SWERVE_FL_WHEEL_MOTOR_CAN_ID = 4;
    public static final int SWERVE_BL_WHEEL_MOTOR_CAN_ID = 1;
    public static final int SWERVE_BR_WHEEL_MOTOR_CAN_ID = 2;
    public static  Gains visionGainsZ = new Gains("visionZ", 0, 0, 0);//-0.002
    public static  Gains visionGainsX = new Gains("visionX", -0.05, 0, 0); //-0.05
    public static  Gains visionGainsY = new Gains("visionY", 0.0, 0, 0); // 0.02


    public static final int SHOOT_MOTOR_MASTER_ID = 6;
    public static final int SHOOT_MOTOR_SLAVE_ID = 5;
    public static final Gains SHOOT_GAINS = new Gains("put values", 0.05, 0, 0);

    public static final int CARTRIDGE_MOTOR_ID = 7;
    public static final int KICKER_MOTOR_ID = 8;
    //public static final int COLLECTION_LEFT_MOTOR_ID = 6;
    public static final int COLLECTION_RIGHT_MOTOR_ID = 9;
    public static final int MICRO_SWITCH_CHANNEL = 9;

    public static final int SWERVE_FR_ANGLE_MOTOR_CHANNEL = 3;
    public static final int SWERVE_FL_ANGLE_MOTOR_CHANNEL = 2;
    public static final int SWERVE_BL_ANGLE_MOTOR_CHANNEL = 0;
    public static final int SWERVE_BR_ANGLE_MOTOR_CHANNEL = 1;

    public static final int SWERVE_FR_ANGLE_ENCODER_CHANNEL = 2;
    public static final int SWERVE_FL_ANGLE_ENCODER_CHANNEL = 3;
    public static final int SWERVE_BL_ANGLE_ENCODER_CHANNEL = 1;
    public static final int SWERVE_BR_ANGLE_ENCODER_CHANNEL = 0;

    public static final double SWERVE_FR_ANGLE_ENCODER_OFFSET = -68.5;//-17.7;
    public static final double SWERVE_FL_ANGLE_ENCODER_OFFSET = -43.5;//-10.2;
    public static final double SWERVE_BL_ANGLE_ENCODER_OFFSET = -297.7;//-74.5;
    public static final double SWERVE_BR_ANGLE_ENCODER_OFFSET = -7.7;//-2.2;

    public static final boolean SWERVE_FR_INVERTED = false;
    public static final boolean SWERVE_FL_INVERTED = false;
    public static final boolean SWERVE_BL_INVERTED = false;
    public static final boolean SWERVE_BR_INVERTED = false;

    public static final double DEGREES_PER_ENCODER_ROTATION = -360;//-360
    public static final double ENCODER_TO_METER = 1;
    public static final double LEN_MODULE_FROM_CENTER = 1;
    public static final int NUMBER_OF_MODULES = 4;
    public static final Gains SWERVE_ANGLE_PID_GAINS = new Gains("SWERVE_ANGLE_PID_GAINS", 0.008, 0, 0.000);

    public static final double KICKER_SPEED = -1;
    public static final double CARTRIDGE_SPEED = 0.8;
    public static final double COLLECT_SPEED = -1;
}