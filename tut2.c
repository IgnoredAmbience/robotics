#include "move.c"

task main()
{
  init_position();

  forward(400);
  rotate(90);
  forward(400);
  rotate(90);
  forward(400);
  rotate(90);
  forward(400);
  rotate(90);
  stop(1000);
}
