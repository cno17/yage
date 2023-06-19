#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

out vec3 lightIntensity;

uniform vec4 lightPosV;
uniform vec3 dInt;            // diffuse light intensity
uniform vec3 dRef;            // diffuse material reflectivity

uniform mat4 matVM;
uniform mat4 matCV;

void main() {
    vec4 posV = matVM * posM;
    vec4 norV = normalize(matVM * norM);
    vec4 dirV = normalize(lightPosV - posV);
    lightIntensity = dInt * dRef * max(dot(dirV, norV), 0.0); // 0
    gl_Position = matCV * posV;
}
