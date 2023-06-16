# About

## ToDo

- Texture Atlas (Sprite Sheet)

- BufferTarget, BufferBindingPoint, FrameBufferAttachmentPoint

- enum Extension: NV_shader_buffer_store // g-truc/oglsamples/caps.hpp

- Messenger.handlers: add method // dont enforce extending Handler

## Introspection

Resource
  name
  index

Block -> Resource
  
## Primitive

## Interface Blocks

src: opengl wiki

an interface block ...

- cannot contain opaque types
- cannot contain nested structure definitions
- can contain members whoose type is a previously defined structure
- input and output blocks can only be used to aggregate data interfaces
    between shader stages: a vs cannot declare an input block, a fs cannot
    declare an output block
- the only types that are valid in input/output blocks are those which are
    valid as input/output variables.
  
  storage_qualifier block_name {
    <block_members>
  } instance_name?

storage_qualifier:

- defines the type of the block: in | out | uniform | buffer

block_name:

- interface matching

instance_name:

- optional
- namespace
- can be used to define an array of blocks

  uniform MatrixBlock {
    mat4 matA;
    mat4 matB;
  } matrices[3];

if a shader S declares an input block then its predecessor P must declare
a matching output block:

  // vs
  out vertex_data {
    vec3 color;
    vec2 tex_coord;
  } vs_out; // internally used?

  // gs
  in vertex_data {
    vec3 color;
    vec2 tex_coord;
  } gs_in[];  

  uniform blocks and storage blocks are called "buffer-backed blocks" because
  the storage for their contents come from a buffer object

  ...
