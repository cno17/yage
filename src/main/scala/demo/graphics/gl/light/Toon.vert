#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matVM;
uniform mat4 matCV;

out vec4 posV;
out vec4 norV;

void main() {
  posV = matVM * posM;
  norV = normalize(matVM * norM); // how to rewrite this? one more line
  gl_Position = matCV * posV;
}

/*
layout (location = 0) in vec3 posM;
layout (location = 1) in vec3 norM;

uniform mat4 matVM;
uniform mat4 matCV;
uniform mat4 MVP;

out vec3 posV;
out vec3 norV;

void main() {
    posV = (matVM * vec4(posM, 1.0)).xyz;
    norV = normalize((matVM * vec4(norM, 0.0)).xyz);
    gl_Position = matCV * vec4(posV, 1.0);
}
*/