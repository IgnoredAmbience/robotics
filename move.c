#include "move.h"

static state_t robot_position;

void init_position() {
  robot_position.x = 0;
  robot_position.y = 0;
  robot_position.a = 0;
  eraseDisplay();
  update_display();
}

/* Updates existing position, by given deltas */
void update_position(float x, float y, float a) {
  robot_position.x += x;
  robot_position.y += y;
  robot_position.a += a;
  update_display();
}

void update_display() {
  nxtSetPixel((int) (robot_position.x / DISPLAY_SCALE) + 10, (int) (robot_position.y / DISPLAY_SCALE) + 10);
}

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

void move_rot(float left, float right, int rot) {
  nMotorEncoder[motorC] = 0;
  nMotorEncoder[motorB] = 0;

  nMotorEncoderTarget[motorC] = rot;
  nMotorEncoderTarget[motorB] = rot;

  motor[motorC] = left * MOTOR_C_CHANGE * POWER_RATIO; //turn both motors on at 30 percent power
  motor[motorB] = right * POWER_RATIO;

  int prevEncoder = 0;

  while (nMotorRunState[motorC] != runStateIdle && nMotorRunState[motorB] != runStateIdle) //while the encoder wheel turns one revolution
  {
    // Update display position when moving straight-line
    if(left == right) {
      int m = nMotorEncoder[motorC];
      float delta = (m - prevEncoder) * ROT_DISTANCE;
      update_position(delta * cosDegrees(robot_position.a), delta * sinDegrees(robot_position.a), 0);
      prevEncoder = m;
    }
  }
}

// distance in mm
void forward(float distance, bool reverse) {
  int rev = reverse ? -1 : 1;
  //move(rev*100, rev*100, distance/VELOCITY);
  move_rot(rev*100, rev*100, distance/ROT_DISTANCE);
}


// +ve angle for left, -ve for right
// angle in same unit as angularVelocity constant
void rotate(float angle) {
  int left = (angle < 0) ? -1 : 1;

  int rot = angle/ROT_TURN;

  move_rot(left * 100, -left * 100, rot);

  update_position(0, 0, angle);
  PlaySound(soundShortBlip);
  wait10Msec(100);
  PlaySound(soundShortBlip);
}
