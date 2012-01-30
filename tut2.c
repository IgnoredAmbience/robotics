#include "move.c"

void Left90deg()
{
  move(-100, 100, 340);
}

task main()
{
  init_position();

  move_ang(2000);

  wait10Msec(100);
  /*
  forward(400);
  rotate(90);
  forward(400);
  rotate(90);
  forward(400);
  rotate(90);
  forward(400);
  stop(1000);*/
}
