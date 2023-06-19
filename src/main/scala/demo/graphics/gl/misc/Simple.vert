#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matCM;

void main() {
    gl_Position = matCM * posM;
}
