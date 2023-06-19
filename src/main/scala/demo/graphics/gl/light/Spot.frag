#version 460 core

struct ADS {
  vec3 a;
  vec3 d;
  vec3 s;
};

struct SpotLight {
  ADS intensity;
  vec4 posV;
  vec4 dirV; // should be normalized!
  float exponent;  // Angular attenuation exponent
  float cutoff;    // Cutoff angle (between 0 and pi/2)
};

struct Material {
  ADS reflectivity;
  float shininess;
};

in vec4 posV;
in vec4 norV;

uniform SpotLight light;
uniform Material material;

layout (location = 0) out vec4 color;

vec3 blinnPhongSpot(vec4 posV, vec4 norV) {  
  vec3 am = light.intensity.a * material.reflectivity.a; 
  vec3 di = vec3(0);
  vec3 sp = vec3(0);
  vec4 s = normalize(light.posV - posV);
  float cos = dot(-s, light.dirV);
  float ang = acos(cos);
  float spotScale = 0.0;
  if (ang >= 0.0 && ang < light.cutoff) {
    spotScale = pow(cos, light.exponent);
    float sDotN = max(dot(s, norV), 0.0);
    di = material.reflectivity.d * sDotN;
    if (sDotN > 0.0) {
      vec4 v = normalize(-posV);
      vec4 h = normalize(v + s);
      // sp = material.reflectivity.s * pow(max(dot(h, n), 0.0), material.shininess);
    }
  }
  return am + spotScale; //  * Spot.L * (diffuse + spec);
}

void main() {
  color = vec4(blinnPhongSpot(posV, norV), 1);
}
