#version 460 core

layout (location = 0) in vec4 aPos;
layout (location = 4) in vec2 aTec;

out vec2 vTec;

void main() {
    gl_Position = aPos;
    vTec = aTec; 
}
