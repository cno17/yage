#version 460 core

layout (vertices = 4) out;

uniform int inner0 = 4;
uniform int inner1 = 4;
uniform int outer0 = 4;
uniform int outer1 = 4;
uniform int outer2 = 4;
uniform int outer3 = 4;

void main() {
    gl_TessLevelInner[0] = inner0;
    gl_TessLevelInner[1] = inner1;
    gl_TessLevelOuter[0] = outer0;
    gl_TessLevelOuter[1] = outer1;
    gl_TessLevelOuter[2] = outer2;
    gl_TessLevelOuter[3] = outer3;
    // TODO int id = gl_InvocationID;
    gl_out[gl_InvocationID].gl_Position = gl_in[gl_InvocationID].gl_Position;
}
