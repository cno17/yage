#version 460 core

layout (binding = 1) uniform Block1 {
  vec4 a[2];
  float r;
} b1;

layout (binding = 2) uniform Block2 {
  vec4 a;
  vec4 b;
  vec4 c;
} b2;

uniform vec4 u1;

void main() {
  gl_Position = gl_Position + b1.a[0] + b1.a[1] * b1.r;
  gl_Position = gl_Position + b2.a + b2.b + b2.c;
  gl_Position += u1;
}
