#version 460 core

layout(location = 0) in vec4 pos;
layout(location = 4) in vec2 tec;

out vec2 vs_tec;

void main() {
  gl_Position = pos;
  vs_tec = tec;
}
