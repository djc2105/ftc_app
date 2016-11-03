package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.GatorBase;

/**
 * Created by andrew on Nov 03, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.navx_samples.
 */

@Autonomous(name="Auto: Red", group="NOTDANNY")
public class RedAuto extends GatorBase {

    private int auto_case;

    @Override
    public void init() {
        super.init();
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
                beacon();
                auto_case++;
                break;
            case 1:
                beacon();
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
    
    public void beacon() {
        boolean running = true;
        int beacon_case = 0;
        int red_pos = 0;
        while (running) {
            switch (beacon_case) {
                case 0:
                    reset_encoders();
                    beacon_case++;
                    break;
                case 1:
                    if (have_encoders_reset()) {
                        run_with_encoders();
                        reset_yaw();
                        beacon_case++;
                    }
                    break;
                case 2:
//                double error = navx.getYaw() * 0.005;
                    double error = (get_fr_enc() - get_fl_enc()) * 0.001;
                    error = Range.clip(error, -0.2, 0.2);
                    telemetry.addData("3 Error: ", error);
//                double lefts = error < 0 ? 0.06 - error : 0.06;
//                double rights = error > 0 ? 0.06 + error : 0.06;
//                rd.tankDrive(-rights, -lefts);
                    rd.arcadeDrive(0.5, -error);
//                rd.mecanumDrive_Cartesian(0, 0.05, 0, get_fl_enc() - get_fr_enc());
                    if (light.getLightDetected() > K_WHITE_LIGHT) {
                        beacon_case++;
                    }
                    break;
                case 3:
                    rd.mecanumDrive_Cartesian(0, 0, 0, 0);
                    beacon_case++;
                    break;
                case 4: // Might need to move to sense colors?
                    beacon_case++;
                    break;
                case 5:
                    if (left.red() > left.blue()) {
                        red_pos = 1;
                    } else {
                        red_pos = 2;
                    }
                    beacon_case++;
                    break;
                case 6:
                    reset_encoders();
                    beacon_case++;
                    break;
                case 7:
                    if (have_encoders_reset()) {
                        run_with_encoders();
                        beacon_case++;
                    }
                    break;
                case 8: // move to press
                    if (red_pos == 1) {
                        rd.arcadeDrive(-0.1, 0);
                    } else {
                        rd.arcadeDrive(0.1, 0);
                    }
                    beacon_case++;
                    break;
                case 9:
                    if (red_pos == 1) {
                        if (Math.abs(get_fl_enc()) > K_ONE_INCH * 1) {
                            rd.arcadeDrive(0, 0);
                            beacon_case++;
                        }
                    } else {
                        if (Math.abs(get_fl_enc()) > K_ONE_INCH * 2) {
                            rd.arcadeDrive(0, 0);
                            beacon_case++;
                        }
                    }
                    break;
                case 10:
                    leftpush.setPosition(K_LEFT_SERVO_BOOP);
                    reset_encoders();
                    beacon_case++;
                    break;
                case 11:
                    if (have_encoders_reset()) {
                        run_with_encoders();
                        beacon_case++;
                    }
                    break;
                case 12: // boop
                    rd.arcadeDrive(0.2, 0);
                    beacon_case++;
                    break;
                case 13:
                    if (red_pos == 1) {
                        if (Math.abs(get_fl_enc()) > K_ONE_INCH * 5) {
                            rd.arcadeDrive(0, 0);
                            beacon_case++;
                        }
                    } else {
                        if (Math.abs(get_fl_enc()) > K_ONE_INCH * 8) {
                            rd.arcadeDrive(0, 0);
                            beacon_case++;
                        }
                    }
                    break;
                case 14:
                    leftpush.setPosition(K_LEFT_SERVO_STOW);
                    reset_encoders();
                    beacon_case++;
                    break;
                case 15:
                    if (have_encoders_reset()) {
                        run_with_encoders();
                        beacon_case++;
                    }
                    break;
                default:
                    running = false;
                    break;
            }
            telemetry.addData("0 Auto Phase: ", beacon_case);
            telemetry.addData("1 Red Pos: ", red_pos);
        }
    }
}
