---
title: ToolSetCommand.run - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset](../index.html) / [ToolSetCommand](index.html) / [run](./run.html)

# run

`fun run(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Returns a command's subcommands.
Note: run last in your command executor!

### Parameters

`sender` - Person running the command

`command` - Command's name as a string

`label` - Unknown what this is

`args` - Arguments passed into the command

**Return**
True if the command succeeded in running, false otherwise

