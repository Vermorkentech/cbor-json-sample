package tech.vermorken.cbor.samplevents

import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class LoggingEventHandler {

    @EventHandler
    fun handle(event: SampleEvent) {
        println(event)
    }

}