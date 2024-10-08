package tech.vermorken.cbor

import org.axonframework.serialization.Converter
import org.axonframework.serialization.SerializedObject
import org.axonframework.serialization.SerializedType
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer

class JsonCborMultiSerializer(
    private val cborSerializer: JacksonSerializer,
    private val jacksonSerializer: JacksonSerializer
) : Serializer {
    override fun <S : Any?, T : Any?> deserialize(serializedObject: SerializedObject<S>): T {
        val dataAsString = cborSerializer.converter.convert(serializedObject.data, String::class.java)
        return if (dataAsString.startsWith("{")) {
            jacksonSerializer.deserialize(serializedObject)
        } else cborSerializer.deserialize(serializedObject)
    }

    override fun <T : Any?> serialize(`object`: Any?, expectedRepresentation: Class<T>): SerializedObject<T> {
        return cborSerializer.serialize(`object`, expectedRepresentation)
    }

    override fun <T : Any?> canSerializeTo(expectedRepresentation: Class<T>): Boolean {
        return cborSerializer.canSerializeTo(expectedRepresentation)
    }

    override fun classForType(type: SerializedType): Class<*> {
        return cborSerializer.classForType(type)
    }

    override fun typeForClass(type: Class<*>?): SerializedType {
        return cborSerializer.typeForClass(type)
    }

    override fun getConverter(): Converter {
        return cborSerializer.converter
    }


}