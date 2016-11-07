package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by andrew on Nov 06, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@Autonomous(name = "Auto: Center", group = "NOTDANNY")
public class ShootAuto extends GatorBase {

    private int auto_case = 0;
    private double spool_start;

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void start() {
        super.start();
        light.enableLed(true);
        rightpush.setPosition(K_RIGHT_SERVO_STOW);
        leftpush.setPosition(K_LEFT_SERVO_STOW);
        reset_yaw();
        reset_encoders();
    }

    @Override
    public void loop() {
        switch (auto_case) {
            case 0:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(0.5, 0);
                    auto_case++;
                }
                break;
            case 1:
                if (have_encoders_reached(K_ONE_INCH * 29)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 2:
                flyright.setPower(1);
                flyleft.setPower(-1);
                spool_start = getRuntime();
                auto_case++;
                break;
            case 3:
                if (getRuntime() > spool_start + 2) {
                    launch.setPosition(K_LAUNCH_SERVO_ACTIVE);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 4:
                if (getRuntime() > spool_start + 1) {
                    launch.setPosition(K_LAUNCH_SERVO_STOW);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 5:
                if (getRuntime() > spool_start + 1) {
                    lift.setPower(0.3);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 6:
                if (getRuntime() > spool_start + 1) {
                    lift.setPower(0);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 7:
                flyright.setPower(1);
                flyleft.setPower(-1);
                spool_start = getRuntime();
                auto_case++;
                break;
            case 8:
                if (getRuntime() > spool_start + 2) {
                    launch.setPosition(K_LAUNCH_SERVO_ACTIVE);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 9:
                if (getRuntime() > spool_start + 1) {
                    launch.setPosition(K_LAUNCH_SERVO_STOW);
                    flyright.setPower(0);
                    flyleft.setPower(0);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
//            case 5:
//                if (have_encoders_reached(K_ONE_INCH * 54)) {
//                    rd.arcadeDrive(0, 0);
//                    auto_case++;
//                }
//                break;
            default:
                break;
        }
    }
}
