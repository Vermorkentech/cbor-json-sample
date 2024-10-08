package tech.vermorken.cbor.samplevents

import org.axonframework.eventhandling.GenericDomainEventMessage
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.messaging.MetaData
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

const val AGGREGATE_TYPE = "tech.vermorken.MyAggregate"
data class SampleEvent(val id: UUID, val someField: String, val otherField: Int)

@Component
class SampleEventPublisher(val eventGateway: EventGateway) {

    @Scheduled(fixedDelay = 5000)
    fun publishEvents() {
        val aggregateId = UUID.randomUUID().toString()
        println("Publishing events for aggregate $aggregateId")
        for (i in 0..3) {
            eventGateway.publish(GenericDomainEventMessage(AGGREGATE_TYPE, aggregateId, i.toLong(), SampleEvent(UUID.randomUUID(), "value${i}", i), MetaData.with("testKey", "testValue")))
        }
    }
}