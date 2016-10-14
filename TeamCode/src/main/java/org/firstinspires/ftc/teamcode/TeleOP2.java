package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

//TEST TELEOP  with -
@TeleOp(name = "TeleOP2", group = "DANNY")
public class TeleOP2 extends OpMode {



    DcMotor motorleftback;
    DcMotor motorrightback;
    DcMotor motorleftfront;
    DcMotor motorrightfront;

    //What happens when the program starts
    public void init() {

        //Creates names for the motors plugged into cetain ports
        motorleftback = hardwareMap.dcMotor.get("motor_3");
        motorrightback = hardwareMap.dcMotor.get("motor_4");
        motorleftfront = hardwareMap.dcMotor.get("motor_1");
        motorrightfront = hardwareMap.dcMotor.get("motor_2");
    }






    @Override
    //What happens as the program is running
    public void loop() {

        //Get joystick values
        float throttle = gamepad1.left_stick_y;
        //float direction = gamepad1.left_stick_x;

        //get trigger values for left and right
        //float throttleHright = gamepad1.right_trigger;
        //float throttleHleft = gamepad1.left_trigger;


        //Adds joystick values together
        //float right = throttle - direction;
        // float left = throttle + direction;

        //Make sure the values dont go above 1 or below -1 to stop out of bounds errors
        //right = Range.clip (right, 1, -1);
        //left = Range.clip (left, 1, -1);
        throttle = Range.clip(throttle, 1, -1);

        //Scale the values given by the controller
        //right = (float)scaleinput(right);
        //left = (float)scaleinput(left);

        //Set motors to given value
        motorrightback.setPower(throttle);
        motorrightfront.setPower(throttle);
        motorleftback.setPower(-throttle);
        motorleftfront.setPower(-throttle);
    }






    @Override
    //What happens when you stop
    public void stop() {

    }






    /*For Scaling
    public static double scaleinput(double dVal){

        //Sets values that it can scale to
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        //Multiplies the inputs by 16 to find what value it will take from the array
        int index = (int) (dVal * 16.0);

        //Makes sure the number isnt below 0
        if (index < 0) {
            index = -index;
        }

        //Makes sure the number isnt above 16
        if(index > 16){
            index = 16;
        }

        //Flips values if joystick is reversed and sets dScale to the value from the array
        double dScale = 0.00;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }
        return dScale;

    }
    */
}


