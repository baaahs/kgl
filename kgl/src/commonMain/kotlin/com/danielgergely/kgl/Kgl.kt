package com.danielgergely.kgl

interface Kgl {
    fun createShader(type: Int): Shader?
    fun shaderSource(shaderId: Shader, source: String)
    fun compileShader(shaderId: Shader)
    fun deleteShader(shaderId: Shader)

    fun getShaderParameter(shader: Shader, pname: Int): Int

    fun getProgramInfoLog(program: Program) : String?
    fun getShaderInfoLog(shaderId: Shader) : String?

    fun createProgram(): Program?
    fun attachShader(programId: Program, shaderId: Shader)
    fun linkProgram(programId: Program)
    fun useProgram(programId: Program)

    fun getProgramParameter(program: Program, pname: Int): Int

    fun getUniformLocation(programId: Program, name: String): UniformLocation?
    fun getAttribLocation(programId: Program, name: String): Int
    fun bindAttribLocation(programId: Program, index: Int, name: String)

    fun enable(cap: Int)
    fun disable(cap: Int)

    fun enableVertexAttribArray(location: Int)
    fun disableVertexAttribArray(location: Int)

    fun createBuffers(count: Int): Array<GlBuffer>
    fun bindBuffer(target: Int, bufferId: GlBuffer?)
    fun bufferData(target: Int, sourceData: Buffer, size: Int, usage: Int)
    fun deleteBuffer(buffer: GlBuffer)

    fun vertexAttribPointer(location: Int, size: Int, type: Int, normalized: Boolean, stride: Int, offset: Int)

    fun uniform(location: UniformLocation, f: Float) = uniform1f(location, f)
    fun uniform(location: UniformLocation, x: Float, y: Float) = uniform2f(location, x, y)
    fun uniform(location: UniformLocation, x: Float, y: Float, z: Float) = uniform3f(location, x, y, z)
    fun uniform(location: UniformLocation, x: Float, y: Float, z: Float, w: Float) = uniform4f(location, x, y, z, w)
    fun uniform(location: UniformLocation, i: Int) = uniform1i(location, i)
    fun uniform(location: UniformLocation, x: Int, y: Int) = uniform2i(location, x, y)
    fun uniform(location: UniformLocation, x: Int, y: Int, z: Int) = uniform3i(location, x, y, z)
    fun uniform(location: UniformLocation, x: Int, y: Int, z: Int, w: Int) = uniform4i(location, x, y, z, w)

    fun uniform1f(location: UniformLocation, f: Float)
    fun uniform1i(location: UniformLocation, i: Int)

    fun uniform2f(location: UniformLocation, x: Float, y: Float)
    fun uniform2i(location: UniformLocation, x: Int, y: Int)

    fun uniform3f(location: UniformLocation, x: Float, y: Float, z: Float)
    fun uniform3fv(location: UniformLocation, value: FloatArray)
    fun uniform3i(location: UniformLocation, x: Int, y: Int, z: Int)

    fun uniform4f(location: UniformLocation, x: Float, y: Float, z: Float, w: Float)
    fun uniform4i(location: UniformLocation, x: Int, y: Int, z: Int, w: Int)

    fun uniformMatrix3fv(location: UniformLocation, transpose: Boolean, value: FloatArray)
    fun uniformMatrix4fv(location: UniformLocation, transpose: Boolean, value: FloatArray)

    fun blendFunc(sFactor: Int, dFactor: Int)

    fun cullFace(mode: Int)

    fun viewport(x: Int, y: Int, width: Int, height: Int)
    fun clearColor(r: Float, g: Float, b: Float, a: Float)
    fun clear(mask: Int)

    fun createTextures(n: Int) : Array<Texture>
    fun deleteTexture(texture: Texture)
    fun texImage2D(target: Int, level: Int, internalFormat: Int, border: Int, resource: TextureResource)
    fun activeTexture(texture: Int)
    fun bindTexture(target: Int, texture: Texture?)
    fun generateMipmap(target: Int)
    fun texParameteri(target: Int, pname: Int, value: Int)

    fun drawArrays(mode: Int, first: Int, count: Int)

    fun getError(): Int
    fun finish()
}