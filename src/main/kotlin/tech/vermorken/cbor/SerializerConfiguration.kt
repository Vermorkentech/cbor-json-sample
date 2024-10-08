package tech.vermorken.cbor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.cbor.databind.CBORMapper
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Configuration
@Profile("!jackson")
class SerializerConfiguration {

    @Bean
    fun eventSerializer(
        cborSerializer: Serializer,
        @Qualifier("jacksonSerializer") jacksonSerializer: JacksonSerializer
    ): Serializer {
        return JsonCborMultiSerializer(cborSerializer as JacksonSerializer, jacksonSerializer)
    }

    @Bean
    fun jacksonSerializer() : JacksonSerializer {
        val objectMapper = ObjectMapper().findAndRegisterModules()
        return JacksonSerializer.builder().objectMapper(objectMapper).build()
    }

    @Bean
    @Primary
    fun primarySerializer(defaultAxonCBORMapper: CBORMapper) : Serializer {
        return JacksonSerializer.builder().objectMapper(defaultAxonCBORMapper).build()
    }

}