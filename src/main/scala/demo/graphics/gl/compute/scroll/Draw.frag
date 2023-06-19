#version 460 core

in vec2 vTec;

uniform sampler2D sampler;

out vec4 fCol;

void main() {
  fCol = texture(sampler, vTec);
}
