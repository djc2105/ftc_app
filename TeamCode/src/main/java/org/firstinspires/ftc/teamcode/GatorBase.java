package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController.*;

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
    public Servo leftpush;
    public Servo rightpush;
    private I2cAddr leftAddr = I2cAddr.create8bit(0x3c), rightAddr = I2cAddr.create8bit(0x3e);

    public GatorBase() {

    }

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("leftback");
        backRight = hardwareMap.dcMotor.get("rightback");
        frontLeft = hardwareMap.dcMotor.get("leftfront");
        frontRight = hardwareMap.dcMotor.get("rightfront");
        rd = new RobotDrive(frontLeft, backLeft, backRight, frontRight);

        lift = hardwareMap.dcMotor.get("lift");
        flyleft = hardwareMap.dcMotor.get("flyLeft");
        flyright = hardwareMap.dcMotor.get("flyRight");

        leftpush = hardwareMap.servo.get("leftpush");
        rightpush = hardwareMap.servo.get("rightpush");

        light = hardwareMap.lightSensor.get("light");
        left = hardwareMap.colorSensor.get("left");
        right = hardwareMap.colorSensor.get("right");
        left.setI2cAddress(leftAddr);
        right.setI2cAddress(rightAddr);

        flyleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        flyright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
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

        super.stop();
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

    public boolean have_encoders_reached(double fl, double bl, double fr, double br) { // 1 rot = 4pi inches = ? enc
        boolean out = false;

        if (frontLeft != null && backLeft != null && frontRight != null && backRight != null) {
            if (Math.abs(get_fl_enc()) >= fl && Math.abs(get_bl_enc()) >= bl && Math.abs(get_fr_enc()) >= fr && Math.abs(get_br_enc()) >= br) {
                out = true;
            }
        }

        return out;
    }

    public boolean have_encoders_reset() {
        return get_bl_enc() == 0 && get_br_enc() == 0 && get_fl_enc() == 0 && get_fr_enc() == 0;
    }

}
