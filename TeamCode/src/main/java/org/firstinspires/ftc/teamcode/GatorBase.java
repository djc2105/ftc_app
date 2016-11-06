package org.firstinspires.ftc.teamcode;

import com.kauailabs.navx.ftc.AHRS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController.*;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

public class GatorBase extends OpMode {

    public DcMotor frontLeft, backLeft, frontRight, backRight;
    public RobotDrive rd;
    public LightSensor light;
    public ColorSensor left, right;
    public DcMotor lift;
    public DcMotor flyleft;
    public DcMotor flyright;
    public Servo leftpush, rightpush, launch;
    public AHRS navx;
    public UltrasonicSensor ultraLeft, ultraRight;
    private I2cAddr leftAddr = I2cAddr.create8bit(0x3c), rightAddr = I2cAddr.create8bit(0x3e);
    private final int NAVX_DIM_I2C_PORT = 3;
    public static final double K_WHITE_LIGHT = 0.45;
    public static final double K_LEFT_SERVO_STOW = 0;
    public static final double K_LEFT_SERVO_BOOP = 0.65;
    public static final double K_RIGHT_SERVO_STOW = 1.0;
    public static final double K_RIGHT_SERVO_BOOP = 0.5;
<<<<<<< Updated upstream
    public static final double K_LAUNCH_SERVO_ACTIVE = 0.6;
    public static final double K_LAUNCH_SERVO_STOW = 0.5;
    public static final int K_PULSES_PER_REVOLUTION = 1120;
=======
    public static final double K_LAUNCH_SERVO_STOW = 0.8;
    public static final double K_LAUNCH_SERVO_ACTIVE = 0.4;
    public static final int K_PULSES_PER_REVOLUTION = 1098;
>>>>>>> Stashed changes
    public static final double K_DISTANCE_PER_REVOLUTION = 4 * 3.141592;
    public static final double K_ONE_INCH = K_PULSES_PER_REVOLUTION/K_DISTANCE_PER_REVOLUTION;
    public static final double K_NAVX_ERROR_TOLERANCE = 1;

    public GatorBase() {

    }

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("leftback");
        backRight = hardwareMap.dcMotor.get("rightback");
        frontLeft = hardwareMap.dcMotor.get("leftfront");
        frontRight = hardwareMap.dcMotor.get("rightfront");
        rd = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

        lift = hardwareMap.dcMotor.get("lift");
        flyleft = hardwareMap.dcMotor.get("flyLeft");
        flyright = hardwareMap.dcMotor.get("flyRight");

        leftpush = hardwareMap.servo.get("leftpush");
        rightpush = hardwareMap.servo.get("rightpush");
        launch = hardwareMap.servo.get("launch");

        light = hardwareMap.lightSensor.get("light");
        left = hardwareMap.colorSensor.get("left");
        right = hardwareMap.colorSensor.get("right");

        ultraLeft = hardwareMap.ultrasonicSensor.get("ultraLeft");
        ultraRight = hardwareMap.ultrasonicSensor.get("ultraRight");
        left.setI2cAddress(leftAddr);
        right.setI2cAddress(rightAddr);

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        flyleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        flyright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        navx = AHRS.getInstance(hardwareMap.deviceInterfaceModule.get("dim"),
                NAVX_DIM_I2C_PORT,
                AHRS.DeviceDataType.kProcessedData);

        navx.zeroYaw();

        leftpush.setPosition(K_LEFT_SERVO_STOW);
        rightpush.setPosition(K_RIGHT_SERVO_STOW);
        launch.setPosition(K_LAUNCH_SERVO_STOW);

    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        rd.mecanumDrive_Cartesian(0, 0, 0, 0);
        lift.setPower(0);
        flyleft.setPower(0);
        flyright.setPower(0);

        navx.close();

        super.stop();
    }

    public void reset_yaw() {
        navx.zeroYaw();
    }

    public boolean is_navx_calibrating() {
        return navx.isCalibrating();
    }

    public void reset_encoders() {
        if (frontLeft != null) {
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (backLeft != null) {
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (frontRight != null) {
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (backRight != null) {
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
    }

    public void run_with_encoders() {
        if (frontLeft != null) {
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if (backLeft != null) {
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if (frontRight != null) {
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        if (backRight != null) {
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public int get_fl_enc() {
        int out = 0;
        if (frontLeft != null) {
            out = frontLeft.getCurrentPosition();
        }
        return out;
    }

    public int get_bl_enc() {
        int out = 0;
        if (backLeft != null) {
            out = backLeft.getCurrentPosition();
        }
        return out;
    }

    public int get_fr_enc() {
        int out = 0;
        if (frontRight != null) {
            out = frontRight.getCurrentPosition();
        }
        return out;
    }

    public int get_br_enc() {
        int out = 0;
        if (backRight != null) {
            out = backRight.getCurrentPosition();
        }
        return out;
    }

    public boolean have_encoders_reached(double target) { // 1 rot = 4pi inches = 280 enc
        boolean out = false;

        if (frontLeft != null && backLeft != null && frontRight != null && backRight != null) {
            if (Math.abs(get_fl_enc()) >= target && Math.abs(get_bl_enc()) >= target && Math.abs(get_fr_enc()) >= target && Math.abs(get_br_enc()) >= target) {
                out = true;
            }
        }

        return out;
    }

    public boolean have_encoders_reset() {
        return get_bl_enc() == 0 && get_br_enc() == 0 && get_fl_enc() == 0 && get_fr_enc() == 0;
    }

}
