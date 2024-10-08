Small sample Axon Framework application that uses CBOR as the main serialization format, but can also deserialize from JSON. This can allow you to smoothly change your serialization to a more concise format (CBOR) without the need to migrate the entire event store before deployment.
Eventually you will want to migrate your old events to CBOR as well, using the Event Transformation API of Axon Server, which can be done asynchronously after deployment of this serializer.

The application can be run wih the `jackson` profile to disable the cbor serialization, allowing you to publish some sample events in JSON format.

Requires Axon Server to be running.
