#version 460 core

layout (location = 0) in vec2 pos;
layout (location = 1) in vec4 col;

out vec4 vs_col;

void main() {
  gl_Position = vec4(pos, 0, 1);
  vs_col = col;
}
