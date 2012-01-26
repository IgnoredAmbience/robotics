

/*--------------------------------------------------------------------------------------------------------*\
|*                                                                                                        *|
|*                                   - Moving Forward then Backward-                                      *|
|*                                            ROBOTC on NXT                                               *|
|*                                                                                                        *|
|*  This program runs your taskbot in a square five times.                                                *|
|*                                                                                                        *|
|*                                        ROBOT CONFIGURATION                                             *|
|*    NOTES:                                                                                              *|
|*    1)  To change the forward movement speed replace the two "100"'s with the desired speed number.     *|
|*        (Negative numbers are the reverse equivalent.)                                                  *|
|*    2)  To change the duration of the movement, replace the "4000" with the desired number of           *|
|*        milliseconds.                                                                                   *|
|*                                                                                                        *|
|*    MOTORS & SENSORS:                                                                                   *|
|*    [I/O Port]              [Name]              [Type]              [Description]                       *|
|*    Port C                  motorC              NXT                 Right motor                         *|
|*    Port B                  motorB              NXT                 Left motor                          *|
\*---------------------------------------------------------------------------------------------------4246-*/

float motor_C_change = 1;
float powerRatio = 0.5;

void Forward40cm()
{
  motor[motorC] = 100 * powerRatio * motor_C_change;     // Motor C is run at a 100 power level.
  motor[motorB] = 100 * powerRatio;     // Motor B is run at a 100 power level.
  wait1Msec(1000);         // The program waits 4000 milliseconds before running further code.
}

void Backward40cm()
{
  motor[motorC] = -100 * powerRatio * motor_C_change;
  motor[motorB] = -100 * powerRatio;
  wait1Msec(1000);
}

void Left90deg()
{
  motor[motorC] = -100 * powerRatio * motor_C_change;
  motor[motorB] = 100 * powerRatio;
  wait1Msec(200);
}

void Right90deg()
{
  motor[motorC] = 100 * powerRatio * motor_C_change;
  motor[motorB] = 100 * powerRatio;
}

void resetMotor()
{
  motor[motorC] = 0;
  motor[motorB] = 0;
  wait1Msec(1000 * powerRatio);
}



task main()
{
  while(true) {
    Forward40cm();
    resetMotor();
    Backward40cm();
    resetMotor();
  }
}
