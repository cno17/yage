#version 460 core

layout (location = 0) in vec2 pos;

void main() {
    gl_Position = vec4(pos, 0.0, 1.0);
    gl_PointSize = 10;
}
