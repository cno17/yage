#version 460 core

in vec2 vs_tec;

uniform sampler2D sampler;

out vec4 fs_col;


void main() {
  fs_col = texture(sampler, vs_tec);
}
