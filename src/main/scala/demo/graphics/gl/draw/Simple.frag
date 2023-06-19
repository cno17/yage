#version 460 core

in vec4 vs_col;

out vec4 fs_col;

void main() {
  // fs_col = vs_col; // vec4(0.0, 1.0, 0.0, 1.0);
  fs_col = vec4(0.0, 1.0, 0.0, 1.0);
}
