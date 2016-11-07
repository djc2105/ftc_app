package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by andrew on Nov 03, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.navx_samples.
 */

@Autonomous(name = "Auto: Red", group = "NOTDANNY")
public class RedAuto extends GatorBase {

    private int auto_case = -4;
    private int beacon_case = 0;
    private int red_pos;
    private double spool_start;

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
            case -4:
                reset_encoders();
                auto_case++;
                break;
            case -3:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(0.3, 0);
                    auto_case++;
                }
                break;
            case -2:
                if (have_encoders_reached(K_ONE_INCH * 3)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case -1:
                if (navx_turn(0.2, -50)) {
                    rd.arcadeDrive(0, 0);
                    reset_encoders();
                    auto_case++;
                }
                break;
            case 0:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(0.5, 0);
                    auto_case++;
                }
                break;
            case 1:
                if (have_encoders_reached(K_ONE_INCH * 64)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 2:
                if (navx_turn(0.2, 0)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 3:
                if (ultraLeft.getUltrasonicLevel() > 6) {
                    rd.mecanumDrive_Cartesian(0.5, 0, navx.getYaw() * 0.05, 0);
                } else {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 4:
                if (navx_turn(0.3, 0)) {
                    rd.arcadeDrive(0, 0);
                    reset_encoders();
                    auto_case++;
                }
                break;
            case 5:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(-0.3, 0);
                    auto_case++;
                }
                break;
            case 6:
                if (have_encoders_reached(K_ONE_INCH * 3)) {
                    rd.arcadeDrive(0, 0);
                    auto_case+=2;
                }
                break;
//            case 5:
//                if (ultraLeft.getUltrasonicLevel() != 6) {
//                    rd.mecanumDrive_Cartesian((ultraLeft.getUltrasonicLevel() - 6) * 0.1, 0, navx.getYaw() * 0.05, 0);
//                } else {
//                    rd.arcadeDrive(0, 0);
//                    auto_case++;
//                }
//                break;
//            case 6:
//                if (navx_turn(0.3, 0)) {
//                    rd.arcadeDrive(0, 0);
//                    reset_yaw();
//                    auto_case++;
//                }
//                break;
//            case 7:
//                auto_case++;
//                break;
            case 8:
                if (beacon(beacon_case, 0)) {
                    auto_case++;
                }
                break;
            case 9:
                beacon_case = 0;
                red_pos = 0;
                auto_case++;
                break;
            case 10:
                if (beacon(beacon_case, 1)) {
                    reset_encoders();
                    auto_case++;
                }
                break;
            case 11:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.mecanumDrive_Cartesian(-0.5, -0.2, navx.getYaw() * 0.05, 0);
                    auto_case++;
                }
                break;
            case 12:
                if (have_encoders_reached(K_ONE_INCH*2)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 13:
                if (navx_turn(0.3, 135)) {
                    rd.arcadeDrive(0, 0);
                    reset_yaw();
                    reset_encoders();
                    auto_case++;
                }
                break;
            case 14:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    rd.arcadeDrive(0.5, 0);
                    auto_case++;
                }
                break;
            case 15:
                if (have_encoders_reached(K_ONE_INCH * 45)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            case 16:
                flyright.setPower(1);
                flyleft.setPower(-1);
                spool_start = getRuntime();
                auto_case++;
                break;
            case 17:
                if (getRuntime() > spool_start + 2) {
                    launch.setPosition(K_LAUNCH_SERVO_ACTIVE);
                    spool_start = getRuntime();
                    auto_case++;
                }
                break;
            case 18:
                if (getRuntime() > spool_start + 1) {
                    launch.setPosition(K_LAUNCH_SERVO_STOW);
                    flyright.setPower(0);
                    flyleft.setPower(0);
                    spool_start = getRuntime();
                    rd.arcadeDrive(0.5, 0);
                    auto_case++;
                }
                break;
            case 19:
                if (have_encoders_reached(K_ONE_INCH * 72)) {
                    rd.arcadeDrive(0, 0);
                    auto_case++;
                }
                break;
            default:
                break;
        }
        telemetry.addData("0 Auto Phase: ", auto_case);
    }

    @Override
    public void stop() {
        super.stop();
    }

    public boolean beacon(int bcase, int type) {
        boolean done = false;
        switch (bcase) {
            case 0:
                reset_encoders();
                beacon_case++;
                break;
            case 1:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    beacon_case++;
                }
                break;
            case 2: // drive up to beacon, following wall
//                double error = navx.getYaw() * 0.005;
//                double error = (get_fr_enc() - get_fl_enc()) * 0.001;
                double error = (6 - ultraLeft.getUltrasonicLevel()) * -0.1;
                error = Range.clip(error, -0.2, 0.2);
                telemetry.addData("3 Error: ", error);
//                double lefts = error < 0 ? 0.06 - error : 0.06;
//                double rights = error > 0 ? 0.06 + error : 0.06;
//                rd.tankDrive(-rights, -lefts);
                rd.arcadeDrive(0.3, -error);
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
            case 5: // check colors
                if (left.red() > left.blue()) {
                    red_pos = 1;
                } else {
                    red_pos = 2;
                }
                beacon_case++;
                break;
            case 6: // turn to align with beacon
                if (navx_turn(0.2, -0.2)) {
                    beacon_case++;
                }
//                beacon_case++;
                break;
            case 7:
                reset_encoders();
                beacon_case++;
                break;
            case 8:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    beacon_case++;
                }
                break;
            case 9: // move to press
                if (red_pos == 1) {
                    rd.arcadeDrive(-0.1, 0);
                } else {
                    rd.arcadeDrive(0.1, 0);
                }
                beacon_case++;
                break;
            case 10:
                if (red_pos == 1) {
                    if (Math.abs(get_fl_enc()) > K_ONE_INCH * 0.7) {
                        rd.arcadeDrive(0, 0);
                        beacon_case++;
                    } else {
                        error = (6 - ultraLeft.getUltrasonicLevel()) * -0.1;
                        error = Range.clip(error, -0.04, 0.04);
                        telemetry.addData("3 Error: ", error);
                        rd.arcadeDrive(-0.1, 0);
                    }
                } else {
                    if (Math.abs(get_fl_enc()) > K_ONE_INCH * 4.5) {
                        rd.arcadeDrive(0, 0);
                        beacon_case++;
                    } else {
                        error = (6 - ultraLeft.getUltrasonicLevel()) * -0.1;
                        error = Range.clip(error, -0.04, 0.04);
                        telemetry.addData("3 Error: ", error);
                        rd.arcadeDrive(0.1, error);
                    }
                }
                break;
            case 11: // deploy servo
                leftpush.setPosition(K_LEFT_SERVO_BOOP);
                reset_encoders();
                beacon_case++;
                break;
            case 12:
                if (have_encoders_reset()) {
                    run_with_encoders();
                    beacon_case++;
                }
                break;
            case 13: // boop
                if (red_pos == 1) {
                    rd.arcadeDrive(-0.2, 0);
                } else {
                    rd.arcadeDrive(0.2, 0);
                }
                beacon_case++;
                break;
            case 14: // drive past, servo out
                if (red_pos == 1) {
                    if (Math.abs(get_fl_enc()) > K_ONE_INCH * 2) {
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
            case 15: // realign
                leftpush.setPosition(K_LEFT_SERVO_STOW);
                if (navx_turn(0.2, 0)) {
                    rd.mecanumDrive_Cartesian(0, 0, 0, 0);
                    beacon_case++;
                }
//                beacon_case++;
                break;
            case 16:
                reset_encoders();
                beacon_case++;
                break;
            case 17: // drive past line if red
                if (have_encoders_reset()) {
                    run_with_encoders();
                    if (red_pos == 1) {
                        rd.arcadeDrive(0.3, 0);
                    }
                    beacon_case++;
                }
                break;
            case 18:
                if (red_pos == 1) {
                    if (Math.abs(get_fl_enc()) > K_ONE_INCH * 6) {
                        rd.arcadeDrive(0, 0);
                        beacon_case++;
                    }
                } else {
                    beacon_case++;
                }
                break;
            default:
                done = true;
                break;
        }
        telemetry.addData("1 Red Pos: ", red_pos);
        return done;

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


//            case 3:
//                if (navx_turn(0.3, -45)) {
//                    rd.arcadeDrive(0, 0);
//                    reset_yaw();
//                    reset_encoders();
//                    auto_case++;
//                }
//                break;
//            case 4:
//                if (have_encoders_reset()) {
//                    run_with_encoders();
//                    rd.arcadeDrive(0.5, 0);
//                    auto_case++;
//                }
//                break;
//            case 5:
//                if (have_encoders_reached(K_ONE_INCH * 24)) {
//                    rd.arcadeDrive(0, 0);
//                    auto_case++;
//                }
//                break;
//            case 6:
//                if (navx_turn(0.3, 45)) {
//                    rd.arcadeDrive(0, 0);
//                    reset_yaw();
//                    auto_case++;
//                }
//                break;
