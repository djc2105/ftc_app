package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@Autonomous(name="Test: One Beacon", group="NOTDANNY")
public class OneButtonAutoTest extends GatorBase {

    private int auto_case = 0;
    private static double K_WHITE_LIGHT = 0.45;
    private int red_pos = 0; // left side means we're on red

    @Override
    public void init() {

        super.init();

    }

    @Override
    public void start() {
        super.start();
        light.enableLed(true);
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
                    auto_case++;
                }
                break;
            case 2:
                rd.mecanumDrive_Cartesian(0, 0.5, 0, 0);
                auto_case++;
                break;
            case 3:
                if (light.getLightDetected() > K_WHITE_LIGHT) {
                    rd.mecanumDrive_Cartesian(0, 0, 0, 0);
                    auto_case++;
                }
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
                auto_case++;
                break;
            default:
                break;
        }

    }

    @Override
    public void stop() {
        super.stop();
    }
    
}

