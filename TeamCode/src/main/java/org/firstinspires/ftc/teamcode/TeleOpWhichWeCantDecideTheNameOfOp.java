package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by andrew on Oct 21, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@TeleOp(name = "TeleOPBoop", group = "NOTDANNY")
public class TeleOpWhichWeCantDecideTheNameOfOp extends OpMode {

    DcMotor FR, BR, FL, BL;
    RobotDrive RD;

    @Override
    public void init() {

        FR = hardwareMap.dcMotor.get("rightfront");
        BR = hardwareMap.dcMotor.get("rightback");
        FL = hardwareMap.dcMotor.get("leftfront");
        BL = hardwareMap.dcMotor.get("leftback");
        RD = new RobotDrive(FL, BL, FR, BR);

    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

        double x = gamepad1.left_stick_x;
        double y  = gamepad1.left_stick_y;
        double rot = gamepad1.right_stick_x;

        RD.mecanumDrive_Cartesian(x, y, rot, 0);

    }

    @Override
    public void stop() {
        super.stop();
    }
}
