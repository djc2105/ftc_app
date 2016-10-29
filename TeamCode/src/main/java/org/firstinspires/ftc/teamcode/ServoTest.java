package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by andrew on Oct 27, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@TeleOp(name = "ServoTestOp", group = "NOTDANNY")
public class ServoTest extends GatorBase {

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
        rightpush.setPosition(gamepad1.right_stick_x);
        telemetry.addData("0 Servo Pos: ", gamepad1.right_stick_x);
        super.loop();
    }

    @Override
    public void stop() {
        super.stop();
    }
}
