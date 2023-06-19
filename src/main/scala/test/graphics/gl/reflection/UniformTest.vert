#version 460 core

// from OpenGl Wiki Program Introspection

layout (location = 0) in vec4 posM;
layout (location = 1) in vec2 tec;

layout (std430) buffer LightBlock {
  vec4 ViewPosition;
  vec4 LightPositions[];
};

struct Struct1 {
  vec4 first;
  vec2 second;
  vec2 third[3];
};

uniform Struct1 u1;

struct Inner {
  vec2 inOne;
  vec4 inTwo;
};

struct Outer {
  vec4 first;
  Inner data;
  vec2 second;
  vec2 third[3];
};

uniform Outer u2;

struct Aggregate {
  vec2 main;
  vec2 sec[3];
};

uniform vec3 u3[7];
uniform Aggregate u4;

uniform Aggregate u5[5];

void main() {
  for (uint i = 0; i < LightPositions.length(); i++) {
    // 
  }

  gl_Position = posM;
}
