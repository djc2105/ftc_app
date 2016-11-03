package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by andrew on Nov 03, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@Autonomous(name="Test: Driving", group="NOTDANNY")
public class AutoTest extends GatorBase {

    private int auto_case = 0;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
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
                rd.arcadeDrive(0.5, 0);
                auto_case++;
                break;
            case 3:
                if (have_encoders_reached(K_ONE_INCH * 12, K_ONE_INCH * 12, K_ONE_INCH * 12, K_ONE_INCH * 12)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
            default:
                break;
        }
    }

    @Override
    public void stop() {
        super.stop();
    }
}
