package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.LightSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController.*;

/**
 * Created by andrew on Oct 22, 2016 as part of ftc_app in org.firstinspires.ftc.teamcode.
 */

public class GatorBase extends OpMode {

    public DcMotor frontLeft, backLeft, frontRight, backRight;
    public RobotDrive rd;
    public LightSensor light;
    public ColorSensor left, right;
    public DcMotor lift;
    public DcMotor flyleft;
    public DcMotor flyright;
    public Servo leftpush;
    public Servo rightpush;

    public GatorBase() {

    }

    @Override
    public void init() {
        backLeft = hardwareMap.dcMotor.get("leftback");
        backRight = hardwareMap.dcMotor.get("rightback");
        frontLeft = hardwareMap.dcMotor.get("leftfront");
        frontRight = hardwareMap.dcMotor.get("rightfront");
        rd = new RobotDrive(frontLeft, backLeft, backRight, frontRight);

        lift = hardwareMap.dcMotor.get("lift");
        flyleft = hardwareMap.dcMotor.get("flyleft");
        flyright = hardwareMap.dcMotor.get("flyright");

        leftpush = hardwareMap.servo.get("leftpush");
        rightpush = hardwareMap.servo.get("rightpush");

        light = hardwareMap.lightSensor.get("light");
        left = hardwareMap.colorSensor.get("left");
        right = hardwareMap.colorSensor.get("right");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        super.stop();
    }

    //--------------------------------------------------------------------------
    //
    // a_left_drive_power
    //

    /**
     * Access the left drive motor's power level.
     */
    double a_left_drive_power() {
        double l_return = 0.0;

        if (frontLeft != null) {
            l_return = frontLeft.getPower();
        }

        return l_return;

    } // a_left_drive_power

    //--------------------------------------------------------------------------
    //
    // a_right_drive_power
    //

    /**
     * Access the right drive motor's power level.
     */
    double a_right_drive_power() {
        double l_return = 0.0;

        if (frontRight != null) {
            l_return = frontRight.getPower();
        }

        return l_return;

    } // a_right_drive_power

    //--------------------------------------------------------------------------
    //
    // set_drive_power
    //

    /**
     * Scale the joystick input using a nonlinear algorithm.
     */
    void set_drive_power(double p_left_power, double p_right_power)

    {
        if (frontLeft != null) {
            frontLeft.setPower(p_left_power);
        }
        if (frontRight != null) {
            frontRight.setPower(p_right_power);
        }
        if (backLeft != null) {
            backLeft.setPower(p_left_power);
        }
        if (backRight != null) {
            backRight.setPower(p_right_power);
        }

    } // set_drive_power

    //--------------------------------------------------------------------------
    //
    // run_using_left_drive_encoder
    //

    /**
     * Set the left drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_left_drive_encoder()

    {
        if (frontLeft != null) {
            frontLeft.setMode
                    (DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

    } // run_using_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_using_right_drive_encoder
    //

    /**
     * Set the right drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_using_right_drive_encoder()

    {
        if (frontRight != null) {
            frontRight.setMode
                    (DcMotorController.RunMode.RUN_USING_ENCODERS
                    );
        }

    } // run_using_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_using_encoders
    //

    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_using_encoders()

    {
        //
        // Call other members to perform the action on both motors.
        //
        run_using_left_drive_encoder();
        run_using_right_drive_encoder();

    } // run_using_encoders

    //--------------------------------------------------------------------------
    //
    // run_without_left_drive_encoder
    //

    /**
     * Set the left drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_without_left_drive_encoder()

    {
        if (frontLeft != null) {
            if (frontLeft.getMode() ==
                    DcMotorController.RunMode.RESET_ENCODERS) {
                frontLeft.setMode
                        (DcMotorController.RunMode.RUN_WITHOUT_ENCODERS
                        );
            }
        }

    } // run_without_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_without_right_drive_encoder
    //

    /**
     * Set the right drive wheel encoder to run, if the mode is appropriate.
     */
    public void run_without_right_drive_encoder()

    {
        if (frontRight != null) {
            if (frontRight.getMode() ==
                    DcMotorController.RunMode.RESET_ENCODERS) {
                frontRight.setMode
                        (DcMotorController.RunMode.RUN_WITHOUT_ENCODERS
                        );
            }
        }

    } // run_without_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // run_without_drive_encoders
    //

    /**
     * Set both drive wheel encoders to run, if the mode is appropriate.
     */
    public void run_without_drive_encoders()

    {
        //
        // Call other members to perform the action on both motors.
        //
        run_without_left_drive_encoder();
        run_without_right_drive_encoder();

    } // run_without_drive_encoders

    //--------------------------------------------------------------------------
    //
    // reset_left_drive_encoder
    //

    /**
     * Reset the left drive wheel encoder.
     */
    public void reset_left_drive_encoder()

    {
        if (frontLeft != null) {
            frontLeft.setMode
                    (DcMotorController.RunMode.RESET_ENCODERS
                    );
        }

    } // reset_left_drive_encoder

    //--------------------------------------------------------------------------
    //
    // reset_right_drive_encoder
    //

    /**
     * Reset the right drive wheel encoder.
     */
    public void reset_right_drive_encoder()

    {
        if (frontRight != null) {
            frontRight.setMode
                    (DcMotorController.RunMode.RESET_ENCODERS
                    );
        }

    } // reset_right_drive_encoder

    //--------------------------------------------------------------------------
    //
    // reset_drive_encoders
    //

    /**
     * Reset both drive wheel encoders.
     */
    public void reset_drive_encoders()

    {
        //
        // Reset the motor encoders on the drive wheels.
        //
        reset_left_drive_encoder();
        reset_right_drive_encoder();

    } // reset_drive_encoders

    //--------------------------------------------------------------------------
    //
    // a_left_encoder_count
    //

    /**
     * Access the left encoder's count.
     */
    int a_left_encoder_count() {
        int l_return = 0;

        if (frontLeft != null) {
            l_return = frontLeft.getCurrentPosition();
        }

        return l_return;

    } // a_left_encoder_count

    //--------------------------------------------------------------------------
    //
    // a_right_encoder_count
    //

    /**
     * Access the right encoder's count.
     */
    int a_right_encoder_count()

    {
        int l_return = 0;

        if (frontRight != null) {
            l_return = frontRight.getCurrentPosition();
        }

        return l_return;

    } // a_right_encoder_count

    //--------------------------------------------------------------------------
    //
    // has_left_drive_encoder_reached
    //

    /**
     * Indicate whether the left drive motor's encoder has reached a value.
     */
    boolean has_left_drive_encoder_reached(double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (frontLeft != null) {
            //
            // Has the encoder reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs(frontLeft.getCurrentPosition()) > p_count) {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_left_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // has_right_drive_encoder_reached
    //

    /**
     * Indicate whether the right drive motor's encoder has reached a value.
     */
    boolean has_right_drive_encoder_reached(double p_count)

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        if (frontRight != null) {
            //
            // Have the encoders reached the specified values?
            //
            // TODO Implement stall code using these variables.
            //
            if (Math.abs(frontRight.getCurrentPosition()) > p_count) {
                //
                // Set the status to a positive indication.
                //
                l_return = true;
            }
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_right_drive_encoder_reached

    //--------------------------------------------------------------------------
    //
    // have_drive_encoders_reached
    //

    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean have_drive_encoders_reached
    (double p_left_count
            , double p_right_count
    )

    {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Have the encoders reached the specified values?
        //
        if (has_left_drive_encoder_reached(p_left_count) &&
                has_right_drive_encoder_reached(p_right_count)) {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // have_encoders_reached

    //--------------------------------------------------------------------------
    //
    // drive_using_encoders
    //

    /**
     * Indicate whether the drive motors' encoders have reached a value.
     */
    boolean drive_using_encoders
    (double p_left_power
            , double p_right_power
            , double p_left_count
            , double p_right_count
    )

    {
        //
        // Assume the encoders have not reached the limit.
        //
        boolean l_return = false;

        //
        // Tell the system that motor encoders will be used.
        //
        run_using_encoders();

        //
        // Start the drive wheel motors at full power.
        //
        set_drive_power(p_left_power, p_right_power);

        //
        // Have the motor shafts turned the required amount?
        //
        // If they haven't, then the op-mode remains in this state (i.e this
        // block will be executed the next time this method is called).
        //
        if (have_drive_encoders_reached(p_left_count, p_right_count)) {
            //
            // Reset the encoders to ensure they are at a known good value.
            //
            reset_drive_encoders();

            //
            // Stop the motors.
            //
            set_drive_power(0.0f, 0.0f);

            //
            // Transition to the next state when this method is called
            // again.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // drive_using_encoders

    //--------------------------------------------------------------------------
    //
    // has_left_drive_encoder_reset
    //

    /**
     * Indicate whether the left drive encoder has been completely reset.
     */
    boolean has_left_drive_encoder_reset() {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Has the left encoder reached zero?
        //
        if (a_left_encoder_count() == 0) {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_left_drive_encoder_reset

    //--------------------------------------------------------------------------
    //
    // has_right_drive_encoder_reset
    //

    /**
     * Indicate whether the left drive encoder has been completely reset.
     */
    boolean has_right_drive_encoder_reset() {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Has the right encoder reached zero?
        //
        if (a_right_encoder_count() == 0) {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // has_right_drive_encoder_reset

    //--------------------------------------------------------------------------
    //
    // have_drive_encoders_reset
    //

    /**
     * Indicate whether the encoders have been completely reset.
     */
    boolean have_drive_encoders_reset() {
        //
        // Assume failure.
        //
        boolean l_return = false;

        //
        // Have the encoders reached zero?
        //
        if (has_left_drive_encoder_reset() && has_right_drive_encoder_reset()) {
            //
            // Set the status to a positive indication.
            //
            l_return = true;
        }

        //
        // Return the status.
        //
        return l_return;

    } // have_drive_encoders_reset

    public void update_telemetry()

    {

        //
        // Send telemetry data to the driver station.
        //
        telemetry.addData
                ("01"
                        , "Left Drive: "
                                + a_left_drive_power()
                                + ", "
                                + a_left_encoder_count()
                );
        telemetry.addData
                ("02"
                        , "Right Drive: "
                                + a_right_drive_power()
                                + ", "
                                + a_right_encoder_count()
                );

    } // update_telemetry

    //--------------------------------------------------------------------------
    //
    // update_gamepad_telemetry
    //

    /**
     * Update the telemetry with current gamepad readings.
     */
    public void update_gamepad_telemetry()

    {
        //
        // Send telemetry data concerning gamepads to the driver station.
        //
        telemetry.addData("05", "GP1 Left: " + -gamepad1.left_stick_y);
        telemetry.addData("06", "GP1 Right: " + -gamepad1.right_stick_y);
        telemetry.addData("07", "GP2 Left: " + -gamepad2.left_stick_y);
        telemetry.addData("08", "GP2 X: " + gamepad2.x);
        telemetry.addData("09", "GP2 Y: " + gamepad2.y);
        telemetry.addData("10", "GP1 LT: " + gamepad1.left_trigger);
        telemetry.addData("11", "GP1 RT: " + gamepad1.right_trigger);

    } // update_gamepad_telemetry

    //--------------------------------------------------------------------------
    //
    // set_first_message
    //

    /**
     * Update the telemetry's first message with the specified message.
     */
    public void set_first_message(String p_message)

    {
        telemetry.addData("00", p_message);

    } // set_first_message

    //--------------------------------------------------------------------------
    //
    // set_error_message
    //

    /**
     * Update the telemetry's first message to indicate an error.
     */
    public void set_error_message(String p_message)

    {
        set_first_message("ERROR: " + p_message);

    } // set_error_message

}
