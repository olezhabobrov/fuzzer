package com.stepanov.bbf.bugfinder.server.messages

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.klib.BinaryCompatibleTransformation
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

import kotlin.reflect.KClass

object TransformationClassSerializer: KSerializer<TransformationClass> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("transformation", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TransformationClass {
        val simpleName = decoder.decodeString()
        val transformationPath = BinaryCompatibleTransformation::class.qualifiedName!!
        val qualifiedName = transformationPath.substring(0, transformationPath.lastIndexOf('.') + 1) + simpleName
        try {
            return TransformationClass(Class.forName(qualifiedName).kotlin as KClass<out Transformation>)
        } catch (e: Exception) {
            throw IllegalArgumentException("Got request, consisting non-existent transformation: ${simpleName}")
        }
    }

    override fun serialize(encoder: Encoder, value: TransformationClass) {
        encoder.encodeString(value.clazz.simpleName!!)
    }
}