#version 460 core

in vec2 vs_tec;

out vec4 fs_col;

uniform sampler2D sampler;

void main() {
  fs_col = texture(sampler, vs_tec);
}
