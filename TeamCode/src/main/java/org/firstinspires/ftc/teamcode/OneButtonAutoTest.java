package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@Autonomous(name="Test: One Beacon", group="NOTDANNY")
public class OneButtonAutoTest extends GatorBase {

    private int auto_case = 0;
    private int red_pos = 0; // left side means we're on red

    @Override
    public void init() {

        super.init();
        auto_case = 0;

    }

    @Override
    public void start() {
        super.start();
        light.enableLed(true);
        leftpush.setPosition(K_LEFT_SERVO_STOW);
        rightpush.setPosition(K_RIGHT_SERVO_STOW);
        reset_yaw();
    }

    @Override
    public void loop() {
        switch (auto_case) {
            case 0:
                reset_encoders();
                auto_case++;
                break;
            case 1:
                if(have_encoders_reset()) {
                    run_with_encoders();
                    reset_yaw();
                    auto_case++;
                }
                break;
            case 2:
                double error = navx.getYaw() * 0.07;
                error = Range.clip(error, -0.1, 0.1);
                double lefts = -0.15 + error;
                double rights = -0.15 - error;
                lefts = Range.clip(lefts, -0.15, 0.15);
                rights = Range.clip(rights, -0.15, 0.15);
                telemetry.addData("3 Error: ", error);
                frontLeft.setPower(lefts);
                backLeft.setPower(lefts);
                frontRight.setPower(rights);
                backRight.setPower(rights);
                if (light.getLightDetected() > K_WHITE_LIGHT) {
                    auto_case++;
                }
                break;
            case 3:
                rd.mecanumDrive_Cartesian(0, 0, 0, 0);
                auto_case++;
                break;
            case 4: // Might need to move to sense colors?
                auto_case++;
                break;
            case 5:
                if (left.red() > left.blue()) {
                    red_pos = 1;
                } else {
                    red_pos = 2;
                }
                auto_case++;
                break;
            case 6:
                reset_encoders();
                auto_case++;
                break;
            case 7:
                if(have_encoders_reset()) {
                    run_with_encoders();
                    auto_case++;
                }
                break;
            case 8: // move to press
                auto_case++;
                break;
            case 9: // boop
                leftpush.setPosition(K_LEFT_SERVO_BOOP);
                auto_case++;
                break;
            default:
                break;
        }
        telemetry.addData("0 Auto Phase: ", auto_case);
        telemetry.addData("1 Red Pos: ", red_pos);
    }

    @Override
    public void stop() {
        super.stop();
    }
    
}

