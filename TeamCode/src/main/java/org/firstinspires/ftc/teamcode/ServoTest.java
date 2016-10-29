package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by andrew on Oct 27, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@TeleOp(name = "ServoTestOp", group = "NOTDANNY")
public class ServoTest extends GatorBase {

    int testingMotor = 0;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
        navx.zeroYaw();
    }

    @Override
    public void loop() {
//        if (gamepad1.x) {
//            testingMotor = 0;
//        } else if (gamepad1.y) {
//            testingMotor = 1;
//        } else if (gamepad1.a) {
//            testingMotor = 2;
//        } else if (gamepad1.b) {
//            testingMotor = 3;
//        }
//        leftpush.setPosition(gamepad1.right_stick_x);
//        switch (testingMotor) {
//            case 0:
//                backLeft.setPower(gamepad1.left_stick_y);
//                break;
//            case 1:
//                frontLeft.setPower(gamepad1.left_stick_y);
//                break;
//            case 2:
//                backRight.setPower(gamepad1.left_stick_y);
//                break;
//            case 3:
//                frontRight.setPower(gamepad1.left_stick_y);
//                break;
//            default:
//                break;
//        }
//        telemetry.addData("0 Servo Pos: ", gamepad1.right_stick_x);
//        telemetry.addData("1 Testing Motor: ", testingMotor);
        rd.arcadeDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        telemetry.addData("2 navX Yaw: ", navx.getYaw());
        super.loop();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
