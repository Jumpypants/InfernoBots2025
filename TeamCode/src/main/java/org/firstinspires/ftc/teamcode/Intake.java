package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Intake {
    CRServo leftServo;
    CRServo rightServo;

    double spinPower;

    public Intake (CRServo leftServo, CRServo rightServo, double spinPower) {
        this.leftServo = leftServo;
        this.rightServo = rightServo;

        this.spinPower = spinPower;
    }

    public void drive(Gamepad gamepad2) {
        if (gamepad2.a) {
            leftServo.set(spinPower);
            rightServo.set(-spinPower);
        } else if (gamepad2.b) {
            leftServo.set(-spinPower);
            rightServo.set(spinPower);
        } else {
            leftServo.set(0);
            rightServo.set(0);
        }
    }
}
