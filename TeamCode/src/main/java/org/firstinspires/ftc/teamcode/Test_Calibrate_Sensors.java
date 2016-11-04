package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

@TeleOp(name="Test: Calibrate", group = "NOTDANNY")
public class Test_Calibrate_Sensors extends GatorBase {

    private int[] Lrgb, Rrgb;

    @Override
    public void init() {
        super.init();
        light.enableLed(true);
        left.enableLed(false);
        right.enableLed(false);
    }

    @Override
    public void loop() {
        telemetry.addData("0 Light Val: ", light.getLightDetected());
        telemetry.addData("1 Left Red: ", left.red());
        telemetry.addData("2 Left Green: ", left.green());
        telemetry.addData("3 Left Blue: ", left.blue());
        telemetry.addData("4 Right Red: ", right.red());
        telemetry.addData("5 Right Green: ", right.green());
        telemetry.addData("6 Right Blue: ", right.blue());
        telemetry.addData("7 Ultra Left: ", ultraLeft.getUltrasonicLevel()); // 6 ultra units to wall
        telemetry.addData("8 Ultra Right: ", ultraRight.getUltrasonicLevel()); // 6 ultra units to wall

        telemetry.update();
    }
}
