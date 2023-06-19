#version 460 core

// posM or posN?

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matCN;

void main() {
    gl_Position = matCN * posM;
}
