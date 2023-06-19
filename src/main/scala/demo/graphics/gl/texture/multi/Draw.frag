#version 460 core

in vec2 vs_tec;

uniform sampler2D tex0;
uniform sampler2D tex1;

out vec4 fs_col;

void main() {
  vec4 col0 = texture(tex0, vs_tec);
  vec4 col1 = texture(tex1, vs_tec);
  fs_col = mix(col0, col1, 0.5);
}
