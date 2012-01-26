#include "move.c"


void Left90deg()
{
  move(-100, 100, 340);
}

task main()
{
  move_ang(833);
  stop(2000);
  /*
  forward(400, true);
  stop(200);
  Left90deg();
  stop(200);
  */
  /*for(int i = 0; i < 4; i++){
    Left90deg();
    stop(200);
  }*/
  /*while(true) {
    //Forward40cm();
    //resetMotor();
    //Backward40cm();
    //resetMotor();
    //Right90deg();
  }*/
}
