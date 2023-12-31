#version 460 core

struct LightInfo {
  vec4 Position;  // Light position in eye coords.
  vec3 La;        // Ambient light intesity
  vec3 L;         // Diffuse and specular light intensity
};

struct MaterialInfo {
  vec3 Ka;            // Ambient reflectivity
  vec3 Kd;            // Diffuse reflectivity
};

const int levels = 3;
const float scaleFactor = 1.0 / levels;

in vec4 posV;
in vec4 norV;

uniform LightInfo Light;
uniform MaterialInfo Material;

layout (location = 0) out vec4 FragColor;

vec3 toonShade() {
    vec4 n = normalize(norV);  // already normalized!
    vec4 s = normalize(Light.Position - posV);
    float sDotN = max(dot(s, n), 0.0);
    vec3 ambient = Light.La * Material.Ka;
    vec3 diffuse = Material.Kd * floor( sDotN * levels ) * scaleFactor;
    return ambient + Light.L * diffuse;
}

void main() {
    FragColor = vec4(toonShade(), 1.0);
}




/*
in vec3 Position;
in vec3 Normal;

uniform struct LightInfo {
  vec4 Position;  // Light position in eye coords.
  vec3 La;        // Ambient light intesity
  vec3 L;         // Diffuse and specular light intensity
} Light;

uniform struct MaterialInfo {
  vec3 Ka;            // Ambient reflectivity
  vec3 Kd;            // Diffuse reflectivity
} Material;

const int levels = 3;
const float scaleFactor = 1.0 / levels;

layout( location = 0 ) out vec4 FragColor;

vec3 toonShade( ) {
    vec3 n = normalize( Normal );
    vec3 s = normalize( Light.Position.xyz - Position );
    vec3 ambient = Light.La * Material.Ka;
    float sDotN = max( dot( s, n ), 0.0 );
    vec3 diffuse = Material.Kd * floor( sDotN * levels ) * scaleFactor;

    return ambient + Light.L * diffuse;
}

void main() {
    FragColor = vec4(toonShade(), 1.0);
}
*/