package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TeleOPTest", group = "DANNY")
public class TeleOPTest extends OpMode {


    DcMotor OneOne;
    DcMotor OneTwo;
    DcMotor TwoOne;
    DcMotor TwoTwo;
    com.qualcomm.robotcore.hardware.TouchSensor TouchSensor;

    //What happens when the program starts
    public void init() {

        //Creates names for the motors plugged into cetain ports
        OneOne = hardwareMap.dcMotor.get("OneOne");
        OneTwo = hardwareMap.dcMotor.get("OneTwo");
        TwoOne = hardwareMap.dcMotor.get("TwoOne");
        TwoTwo = hardwareMap.dcMotor.get("TwoTwo");
        TouchSensor = hardwareMap.touchSensor.get("TouchSensor");
    }


    @Override
    //What happens as the program is running
    public void loop() {

        if (TouchSensor.isPressed()) {
            OneOne.setPower(1.00);
            OneTwo.setPower(1.00);
            TwoOne.setPower(1.00);
            TwoTwo.setPower(1.00);
        } else {
            OneOne.setPower(0.00);
            OneTwo.setPower(0.00);
            TwoOne.setPower(0.00);
            TwoTwo.setPower(0.00);
        }


    }


    @Override
    //What happens when you stop
    public void stop() {

    }


}