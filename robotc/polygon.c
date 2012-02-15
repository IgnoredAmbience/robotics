#include "move.c"

task main()
{
  init_position();

  int sides = 0;
  for(int i = 0; i < sides; i++) {
    forward(400);
    rotate(360/sides);
  }
  stop(1000);
}
