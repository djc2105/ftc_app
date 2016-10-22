package org.firstinspires.ftc.teamcode;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

public class Test_Calibrate_Sensors extends GatorBase {

    private int[] Lrgb, Rrgb;

    @Override
    public void init() {
        super.init();
        light.enableLed(true);
    }

    @Override
    public void loop() {
        Lrgb[0] = left.red();
        Lrgb[1] = left.green();
        Lrgb[2] = left.blue();
        Rrgb[0] = right.red();
        Rrgb[1] = right.green();
        Rrgb[2] = right.blue();
        telemetry.addData("0 Light Val: ", light.getLightDetected());
        telemetry.addData("1 Left RGB: ", Lrgb);
        telemetry.addData("2 Right RGB: ", Rrgb);
    }
}
