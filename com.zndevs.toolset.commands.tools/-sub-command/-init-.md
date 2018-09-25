---
title: SubCommand.<init> - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.commands.tools](../index.html) / [SubCommand](index.html) / [&lt;init&gt;](./-init-.html)

# &lt;init&gt;

`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charNames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, commandRunner: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`)`

Creates a subcommand instance without a description

### Parameters

`name` - Name of the command

`charNames` - Aliases of the command

`commandRunner` - The command runner instance`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, desc: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`)`

Creates a subcommand instance without aliases

### Parameters

`name` - Name of the command

`desc` - The command's description

`commandRunner` - The command runner instance`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`)`

Creates a subcommand instance without a description or aliases

### Parameters

`name` - Name of the command

`commandRunner` - The command runner instance`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charNames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, customHandledArgs: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, desc: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`

Creates a subcommand instance

### Parameters

`name` - Name of the command

`charNames` - Aliases of the command

`customHandledArgs` - Arguments that are customly handled

`desc` - The command's description

`commandRunner` - The command runner lambda`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charNames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, desc: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`

Creates a subcommand instance without customly handled arguments

### Parameters

`name` - Name of the command

`charNames` - Aliases of the command

`desc` - The command's description

`commandRunner` - The command runner lambda`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, charNames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`

Creates a subcommand instance without customly handled arguments or a description

### Parameters

`name` - Name of the command

`charNames` - Aliases of the command

`commandRunner` - The command runner lambda`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, desc: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`

Creates a subcommand instance without customly handled arguments or aliases

### Parameters

`name` - Name of the command

`desc` - The command's description

`commandRunner` - The command runner lambda`SubCommand(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`

Creates a subdommand instance with just a name and command runner

### Parameters

`name` - Name of the command

`commandRunner` - The command runner lambda`SubCommand(longName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, shortNames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?, command: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`)`