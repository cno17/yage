#version 460 core

in vec3 lightIntensity;

layout (location = 0) out vec4 color;

void main() {
  color = vec4(lightIntensity, 1.0);
}
