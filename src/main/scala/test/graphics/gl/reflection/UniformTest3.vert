#version 460 core

struct S {
  vec4 a;
  vec4 b[3];
  vec4 c;
};

uniform S u[2];

void main() {
  for (uint i = 0; i < u.length(); i++) {
    gl_Position = gl_Position + u[i].a;
    for (uint j = 0; j < u[i].b.length(); j++) {
      gl_Position = gl_Position + u[i].b[j];
    }
    gl_Position = gl_Position + u[i].c;
  }
}
