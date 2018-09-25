---
title: CommandResponse.broadcastMessage - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [CommandResponse](index.html) / [broadcastMessage](./broadcast-message.html)

# broadcastMessage

`var broadcastMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`

The message to be broadcasted

`fun broadcastMessage(server: Server, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Broadcasts a message to every online player

### Parameters

`server` - Server to broadcast messages on

`message` - Message to broadcast`fun broadcastMessage(server: Server, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, player: Player): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Broadcasts a message to every online player except for the one parsed into the third argument

### Parameters

`server` - Server to broadcast messages on

`message` - Message to broadcast

`player` - Player to omit from broadcasting