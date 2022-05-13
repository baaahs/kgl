package com.danielgergely.kgl

import org.khronos.webgl.*

actual abstract class Buffer(val buffer: ArrayBufferView) {
    actual var position: Int = 0

    actual abstract val size: Int

    actual abstract val sizeInBytes: Int

    inline fun <T> withGlBuffer(offset: Int, fn: (buffer: ArrayBufferView) -> T): T {
        return fn(buffer.asDynamic().subarray(offset))
    }
}

actual class FloatBuffer constructor(buffer: Float32Array) : Buffer(buffer) {
    actual constructor(buffer: Array<Float>) : this(Float32Array(buffer))
    actual constructor(buffer: FloatArray) : this(Float32Array(buffer.toTypedArray()))
    actual constructor(size: Int) : this(FloatArray(size))

    private val floatBuffer: Float32Array = buffer

    override val size: Int
        get() = floatBuffer.length

    override val sizeInBytes: Int
        get() = buffer.byteLength

    actual fun put(f: Float) {
        floatBuffer[position] = f
        position += 1
    }

    actual fun put(floatArray: FloatArray) = put(floatArray, 0, floatArray.size)

    actual fun put(floatArray: FloatArray, offset: Int, length: Int) {
        floatBuffer.set((floatArray.unsafeCast<Float32Array>()).subarray(offset, length), position)
        position += length
    }

    actual operator fun set(pos: Int, f: Float) {
        floatBuffer[pos] = f
    }

    actual fun get(): Float = floatBuffer[position]

    actual fun get(floatArray: FloatArray) {
        get(floatArray, 0, floatArray.size)
    }

    actual fun get(floatArray: FloatArray, offset: Int, length: Int) {
        val dest = floatArray.unsafeCast<Float32Array>()
        dest.subarray(offset, length).set(floatBuffer, position)
    }

    actual operator fun get(pos: Int): Float = floatBuffer[pos]
}

actual class ByteBuffer constructor(buffer: Uint8Array) : Buffer(buffer) {
    actual constructor(buffer: Array<Byte>) : this(Uint8Array(buffer))
    actual constructor(buffer: ByteArray) : this(Uint8Array(buffer.toTypedArray()))
    actual constructor(size: Int) : this(ByteArray(size))

    private val byteBuffer: Uint8Array = buffer

    override val size: Int
        get() = byteBuffer.length

    override val sizeInBytes: Int
        get() = buffer.byteLength

    actual fun put(b: Byte) {
        byteBuffer[position] = b
        position += 1
    }

    actual fun put(byteArray: ByteArray) = put(byteArray, 0, byteArray.size)

    actual fun put(byteArray: ByteArray, offset: Int, length: Int) {
        byteBuffer.set(byteArray.unsafeCast<Uint8Array>().subarray(offset, length), position)
        position += length
    }

    actual operator fun set(pos: Int, b: Byte) {
        byteBuffer[pos] = b
    }

    actual fun get(): Byte {
        return byteBuffer[position]
    }

    actual fun get(byteArray: ByteArray) {
        get(byteArray, 0, byteArray.size)
    }

    actual fun get(byteArray: ByteArray, offset: Int, length: Int) {
        val dest = byteArray.unsafeCast<Uint8Array>()
        dest.subarray(offset, length).set(byteBuffer, position)
    }

    actual operator fun get(pos: Int): Byte {
        return byteBuffer[pos]
    }
}
