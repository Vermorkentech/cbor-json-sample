Small sample  Axon Framework application that uses CBOR as the main serialization format, but can also deserialize from JSON. This can allow you to smoothly change your serilization to a more concise format (CBOR) without the need to migrate the entire event store before deployment.

The application can be run wih the `json` profile to disable the cbor serialization, allowing you to publish some sample events in JSON format.
