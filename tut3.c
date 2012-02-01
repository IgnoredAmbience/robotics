#pragma config(Sensor, S1,     bumper,              sensorTouch)
#include "move.c"

task flash_LED() {
  nMotorPIDSpeedCtrl[motorA] = mtrNoReg;
  while(true) {
    motor[motorA] = 100;
    wait10Msec(10);
    motor[motorA] = 0;
    PlaySound(soundBlip);
    wait10Msec(10);
  }
}

task LED () {
  nMotorPIDSpeedCtrl[motorA] = mtrNoReg;
  while(true) {
    if(SensorValue[bumper]) {
      motor[motorA] = 100;
    } else {
      motor[motorA] = 0;
    }
  }
}

task drive() {
  nSyncedMotors = synchBC;
  while(true) {
    if(SensorValue[bumper]) {
      StartTask(flash_LED);
      move(-100, -100, 1000);
      StopTask(flash_LED);
    } else {
      motor[motorB] = 50;
    }
  }
}

task main() {
  StartTask(LED);
  StartTask(drive);
  while(true);
}

