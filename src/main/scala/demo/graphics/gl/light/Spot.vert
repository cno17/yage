#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matVM;
uniform mat4 matCV;

out vec4 posV;
out vec4 norV;

void main() {
  posV = matVM * posM;
  norV = normalize(matVM * norM);
  gl_Position = matCV * posV;
}
