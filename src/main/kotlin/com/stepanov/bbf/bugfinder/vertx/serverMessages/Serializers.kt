package com.stepanov.bbf.bugfinder.vertx.serverMessages

import com.stepanov.bbf.bugfinder.mutator.transformations.Transformation
import com.stepanov.bbf.bugfinder.mutator.transformations.util.ExpressionReplacer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.net.URI

import kotlin.reflect.KClass

object TransformationClassSerializer: KSerializer<TransformationClass> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("transformation", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): TransformationClass {
        val simpleName = decoder.decodeString()
        val transformationPath = Transformation::class.qualifiedName!!
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

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = URI::class)
object URISerializer: KSerializer<URI> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("URI", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): URI = URI.create(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: URI) {
        encoder.encodeString(value.toString())
    }
}
