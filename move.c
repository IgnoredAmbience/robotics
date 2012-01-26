/* Movement functions */
#define POWER_RATIO 0.5
#define VELOCITY 0.245 // m/s
#define ANG_VELOCITY 1 // rad/s
#define MOTOR_C_CHANGE 1

// TODO: Consider using the motor encoders

void move(float left, float right, int time) {
  motor[motorC] = left * MOTOR_C_CHANGE * POWER_RATIO;
  motor[motorB] = right * POWER_RATIO;
  wait1Msec(time);
}

void stop(int time)
{
  move(0, 0, time);
}

void move_ang(int angle) {
  nMotorEncoder[motorC] = 0;
  nMotorEncoder[motorB] = 0;

  nMotorEncoderTarget[motorC] = angle;
  nMotorEncoderTarget[motorB] = angle;

  motor[motorC] = 100 * POWER_RATIO; //turn both motors on at 30 percent power
  motor[motorB] = 100 * POWER_RATIO;

  while (nMotorRunState[motorC] != runStateIdle && nMotorRunState[motorB] != runStateIdle) //while the encoder wheel turns one revolution
  {
    // This condition waits for motors B + C to come to an idle position. Both motors stop
    // and then jumps out of the loop
  }
  stop(0);
  nxtScrollText("B: %d C: %d", nMotorEncoder[motorB], nMotorEncoder[motorC]);
}

// distance in mm
void forward(float distance, bool reverse = false) {
  int rev = reverse ? -1 : 1;
  move(rev*100, rev*100, distance/VELOCITY);
}


// -ve angle for left, +ve for right
// angle in same unit as angularVelocity constant
void rotate(float angle) {
  int left = (angle < 0) ? -1 : 1;
  float time = abs(angle) / ANG_VELOCITY;
  move(left * 100, -left * 100, time);
}
