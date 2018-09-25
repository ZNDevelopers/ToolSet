---
title: ToolSetOptions.getHelpFormat - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [ToolSetOptions](index.html) / [getHelpFormat](./get-help-format.html)

# getHelpFormat

`fun getHelpFormat(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandOptions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`

Get help text from command

### Parameters

`commandName` - The command name to be displayed

`commandOptions` - The different ways the command can be run. For example: `{ "help <page-number>" or "<player>" }`

**Return**
Command help string

`fun getHelpFormat(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandOptions: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`Node`](../-node/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`

Get help text from a command using Nodes

### Parameters

`commandName` - The command name to be displayed

`commandOptions` - The different ways the command can be run

**Return**
Command help string

