package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

//FULL TELEOP
@TeleOp(name = "TeleOP", group = "DANNY")
public class TeleOP extends OpMode {

    DcMotor leftback;
    DcMotor rightback;
    DcMotor leftfront;
    DcMotor rightfront;
    DcMotor lift;
    DcMotor flyleft;
    DcMotor flyright;
    RobotDrive rd;

    //What happens when the program starts
    public void init() {

        //Creates names for the motors plugged into cetain ports
        leftback = hardwareMap.dcMotor.get("leftback");
        rightback = hardwareMap.dcMotor.get("rightback");
        leftfront = hardwareMap.dcMotor.get("leftfront");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        lift = hardwareMap.dcMotor.get("lift");
        flyleft = hardwareMap.dcMotor.get("flyleft");
        flyright = hardwareMap.dcMotor.get("flyright");
        rd = new RobotDrive(leftfront, leftback, rightfront, rightback);
    }






    @Override
    //What happens as the program is running
    public void loop() {

        //Get joystick values
        float throttle = gamepad1.left_stick_y;
        float direction = gamepad1.left_stick_x;

        //gets gamepad vales for the lift
        boolean lifton = gamepad1.dpad_up;

        //Gtes gampad vales for the fluwheel
        boolean flyon = gamepad1.left_bumper;

        //get mecanum values
        float mecleft = gamepad1.left_trigger;
        float mecright = gamepad1.right_trigger;
        float mec = mecright - mecleft;

        //Set motors to given value
//        rightback.setPower(right);
//        rightfront.setPower(right);
//        leftback.setPower(left);
//        leftfront.setPower(left);
        rd.mecanumDrive_Cartesian(mec, throttle, direction, 0);

        //turn on ball lift if button is pressed
        if (lifton == true) {
            lift.setPower(-1.00);
        }
        else{
            lift.setPower(0.00);
        }

        //turn on flywheel if left trigger is pressed
        if (flyon){
            flyleft.setPower(1.00);
            flyright.setPower(-1.00);
        }
        else{
            flyleft.setPower(0.00);
            flyright.setPower(0.00);
        }
    }






    @Override
    //What happens when you stop
    public void stop() {

    }






    //For Scaling
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

}



