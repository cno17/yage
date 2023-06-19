#version 460 core

// light intensity, material reflectivity

struct Light {
  vec3 a;
  vec3 d;
  vec3 s;
  vec4 posV;
};

struct Material {
  vec3 a;
  vec3 d;
  vec3 s;
  float shininess;
};

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matVM;
uniform mat4 matCV;
uniform Light light;
uniform Material material;

out vec3 lightIntensity;

void main() {
  vec4 posV = matVM * posM;
  vec4 norV = normalize(matVM * norM);
  vec4 dirV = normalize(light.posV - posV);
  float dDotN = max(dot(dirV, norV), 0.0);
  vec3 a = light.a * material.a;
  vec3 d = light.d * material.d * dDotN;
  vec3 s = vec3(0.0);
  if (dDotN > 0.0) {
    vec4 v = normalize(-posV);
    vec4 r = reflect(-dirV, norV);
    s = light.s * material.s * pow(max(dot(r, v), 0.0), material.shininess);
  }
  lightIntensity = a + d + s;
  gl_Position = matCV * posV;
}
