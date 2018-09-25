---
title: ToolSetCommandCommand.getSubCommands - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.commands](../index.html) / [ToolSetCommandCommand](index.html) / [getSubCommands](./get-sub-commands.html)

# getSubCommands

`fun getSubCommands(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`SubCommand`](../../com.zndevs.toolset.commands.tools/-sub-command/index.html)`>`

Overrides [ToolSetCommand.getSubCommands](../../com.zndevs.toolset/-tool-set-command/get-sub-commands.html)

***IMPORTANT**: Do not recreate subcommands when this is run!*

It gets run multiple times. Instead store either an array of `SubCommand`s and directly return it, or store each
`SubCommand` separately and return them in an array (first option is recommended).

Return a list of subcommands that can be accessed with `/<command> <subcommand>`. Will also be added to the help
list.

**Return**
Subcommands in an array

