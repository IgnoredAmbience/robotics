/* Movement functions */
#define POWER_RATIO 0.5
#define VELOCITY 0.245 // m/s
#define ANG_VELOCITY 1 // rad/s

#define ROT_DISTANCE 0.480192077 // mm/rot
#define ROT_TURN 0.486486486 // ang/rot

#define MOTOR_C_CHANGE 1
#define DISPLAY_SCALE 10

/* Position estimation */
typedef struct {
  float x, y, a;
} state_t;

void init_position();
void update_position(float x, float y, float a);
void update_display();
void move(float left, float right, int time);
void stop(int time);
void move_rot(float left, float right, int rot);
void forward(float distance, bool reverse = false);
void reverse(float distance);
void rotate(float angle);
