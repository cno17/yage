#version 460 core

in vec2 vs_tec;

uniform sampler2D tex;

out vec4 fs_col;


void main() {
  fs_col = texture(tex, vs_tec);
}
