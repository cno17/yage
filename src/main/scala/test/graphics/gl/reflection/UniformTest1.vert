#version 460 core

struct S {
  vec4 v1;
  vec4 v2;
  vec4 v3[3];
  vec4 v4;
};

uniform S u;

void main() {
  gl_Position = u.v1 + u.v2 + u.v3[0] + u.v4;
}
