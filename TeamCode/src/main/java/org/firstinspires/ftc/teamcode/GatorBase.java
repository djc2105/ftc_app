package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
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
        flyleft = hardwareMap.dcMotor.get("flyleft");
        flyright = hardwareMap.dcMotor.get("flyright");

        leftpush = hardwareMap.servo.get("leftpush");
        rightpush = hardwareMap.servo.get("rightpush");

        light = hardwareMap.lightSensor.get("light");
        left = hardwareMap.colorSensor.get("left");
        right = hardwareMap.colorSensor.get("right");
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
        super.stop();
    }

}
