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
                if (have_encoders_reached(K_ONE_INCH * 24)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 2:
                flyright.setPower(K_FLYWHEEL_SPEED);
                flyleft.setPower(-K_FLYWHEEL_SPEED);
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
                flyright.setPower(K_FLYWHEEL_SPEED);
                flyleft.setPower(-K_FLYWHEEL_SPEED);
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
            case 10:
                if (navx_turn(0.15, 40)) {
                    rd.arcadeDrive(0, 0);
                    reset_encoders();
                    auto_case++;
                }
                break;
            case 11:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(0.4, 0);
                    auto_case++;
                }
                break;
            case 12:
                if (have_encoders_reached(K_ONE_INCH * 72)) {
                    rd.arcadeDrive(0, 0);
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

    public boolean navx_turn(double power, double target) {
        boolean at_target = navx.getYaw() <= target + K_NAVX_ERROR_TOLERANCE && navx.getYaw() >= target - K_NAVX_ERROR_TOLERANCE;
        if (!at_target) {
            if (navx.getYaw() < target) {
                rd.arcadeDrive(0, power);
            } else {
                rd.arcadeDrive(0, -power);
            }
        } else {
            rd.arcadeDrive(0, 0);
        }
        return at_target;
    }
}
