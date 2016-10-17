package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "RobotDrive", group = "DANNY")
public class RobotDrive {
    //test
    /**
     * The location of a motor on the robot for the purpose of driving
     */
    public static class MotorType {

        /**
         * The integer value representing this enumeration
         */
        public final int value;
        static final int kFrontLeft_val = 0;
        static final int kFrontRight_val = 1;
        static final int kRearLeft_val = 2;
        static final int kRearRight_val = 3;
        /**
         * motortype: front left
         */
        public static final MotorType kFrontLeft = new MotorType(kFrontLeft_val);
        /**
         * motortype: front right
         */
        public static final MotorType kFrontRight = new MotorType(kFrontRight_val);
        /**
         * motortype: rear left
         */
        public static final MotorType kRearLeft = new MotorType(kRearLeft_val);
        /**
         * motortype: rear right
         */
        public static final MotorType kRearRight = new MotorType(kRearRight_val);

        private MotorType(int value) {
            this.value = value;
        }
    }

    /**
     * Constructor for RobotDrive with 4 motors specified with channel numbers.
     * Set up parameters for a four wheel drive system where all four motor pwm
     * channels are specified in the call. This call assumes Talons for
     * controlling the motors.
     *$
     * @param frontLeftMotor Front left motor channel number
     * @param rearLeftMotor Rear Left motor channel number
     * @param frontRightMotor Front right motor channel number
     * @param rearRightMotor Rear Right motor channel number
     */
    public RobotDrive(final DcMotor frontLeftMotor, final DcMotor rearLeftMotor, final DcMotor frontRightMotor,
                      final DcMotor rearRightMotor) {
        m_sensitivity = kDefaultSensitivity;
        m_maxOutput = kDefaultMaxOutput;
        m_rearLeftMotor = rearLeftMotor;
        m_rearRightMotor = rearRightMotor;
        m_frontLeftMotor = frontLeftMotor;
        m_frontRightMotor = frontRightMotor;
    }

    protected static final int kMaxNumberOfMotors = 4;
    public static final double kDefaultSensitivity = 0.5;
    public static final double kDefaultMaxOutput = 1.0;
    protected double m_sensitivity;
    protected double m_maxOutput;
    protected DcMotor m_frontLeftMotor;
    protected DcMotor m_frontRightMotor;
    protected DcMotor m_rearLeftMotor;
    protected DcMotor m_rearRightMotor;

    public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle) {
        double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compenstate for gyro angle.
        double rotated[] = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        wheelSpeeds[MotorType.kFrontLeft_val] = xIn + yIn + rotation;
        wheelSpeeds[MotorType.kFrontRight_val] = -xIn + yIn - rotation;
        wheelSpeeds[MotorType.kRearLeft_val] = -xIn + yIn + rotation;
        wheelSpeeds[MotorType.kRearRight_val] = xIn + yIn - rotation;

        normalize(wheelSpeeds);
        m_frontLeftMotor.setPower(wheelSpeeds[MotorType.kFrontLeft_val] * m_maxOutput);
        m_frontRightMotor.setPower(wheelSpeeds[MotorType.kFrontRight_val] * m_maxOutput);
        m_rearLeftMotor.setPower(wheelSpeeds[MotorType.kRearLeft_val] * m_maxOutput);
        m_rearRightMotor.setPower(wheelSpeeds[MotorType.kRearRight_val] * m_maxOutput);

    }

    /**
     * Normalize all wheel speeds if the magnitude of any wheel is greater than
     * 1.0.
     */
    protected static void normalize(double wheelSpeeds[]) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);
        int i;
        for (i = 1; i < kMaxNumberOfMotors; i++) {
            double temp = Math.abs(wheelSpeeds[i]);
            if (maxMagnitude < temp)
                maxMagnitude = temp;
        }
        if (maxMagnitude > 1.0) {
            for (i = 0; i < kMaxNumberOfMotors; i++) {
                wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
            }
        }
    }

    /**
     * Rotate a vector in Cartesian space.
     */
    protected static double[] rotateVector(double x, double y, double angle) {
        double cosA = Math.cos(angle * (3.14159 / 180.0));
        double sinA = Math.sin(angle * (3.14159 / 180.0));
        double out[] = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }
}
