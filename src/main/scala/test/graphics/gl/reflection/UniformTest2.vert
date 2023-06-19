#version 460 core

struct Inner {
  vec4 v1;
  vec4 v2;
};

struct Outer {
  vec4 v1;
  Inner inner;
  vec4 v2[3];
  vec4 v3;
};

uniform Outer u;

void main() {
  gl_Position = u.v1 + u.inner.v1 + u.inner.v2 + u.v2[0] + u.v3;
}
