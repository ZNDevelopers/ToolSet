---
title: CommandResponse - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [CommandResponse](./index.html)

# CommandResponse

`class CommandResponse`

Tools to help with responses from commands

### Constructors

| [&lt;init&gt;](-init-.html) | `CommandResponse()`<br>Tools to help with responses from commands |

### Properties

| [broadcastMessage](broadcast-message.html) | `var broadcastMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The message to be broadcasted |
| [consoleMessage](console-message.html) | `var consoleMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The message to be sent to the console |
| [playerMessage](player-message.html) | `var playerMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>The message sent to the executing player |

### Functions

| [send](send.html) | `fun send(player: CommandSender, server: Server = Bukkit.getServer()): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sends all messages if they are required |
| [useBroadcastMessage](use-broadcast-message.html) | `fun useBroadcastMessage(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check if the message to be broadcasted exists |
| [useConsoleMessage](use-console-message.html) | `fun useConsoleMessage(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check if the message to be sent to the console exists |
| [usePlayerMessage](use-player-message.html) | `fun usePlayerMessage(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check if the message to send exists |

### Companion Object Functions

| [broadcastMessage](broadcast-message.html) | `fun broadcastMessage(server: Server, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Broadcasts a message to every online player`fun broadcastMessage(server: Server, message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, player: Player): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Broadcasts a message to every online player except for the one parsed into the third argument |
| [getMessage](get-message.html) | `fun getMessage(messageConfig: ConfigurationSection, messageConfigPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, defaultMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Gets a message from the configuration |
| [parseColours](parse-colours.html) | `fun parseColours(message: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Parses colours in messages using the '&amp;' symbol |

