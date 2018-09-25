---
title: ToolSetCommandTools - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [ToolSetCommandTools](./index.html)

# ToolSetCommandTools

`object ToolSetCommandTools`

Tools to help with commands

### Properties

| [commandExecutors](command-executors.html) | `val commandExecutors: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`>` |
| [commandPlugins](command-plugins.html) | `val commandPlugins: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, JavaPlugin>` |
| [toolSetPlugins](tool-set-plugins.html) | `val toolSetPlugins: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, JavaPlugin>` |
| [toolSetPluginsInternal](tool-set-plugins-internal.html) | `val toolSetPluginsInternal: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, JavaPlugin>` |

### Functions

| [addPlugin](add-plugin.html) | `fun addPlugin(pluginName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, plugin: JavaPlugin): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds a plugin to the list of currently running plugins |
| [disableCommand](disable-command.html) | `fun disableCommand(command: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Disables a plugin's command |
| [disableCommandSoft](disable-command-soft.html) | `fun disableCommandSoft(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [enableCommand](enable-command.html) | `fun enableCommand(plugin: JavaPlugin, command: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, executor: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getPlugin](get-plugin.html) | `fun getPlugin(pluginName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): JavaPlugin?`<br>Gets a plugin from the list of currently running plugins |
| [registerIfEnabled](register-if-enabled.html) | `fun registerIfEnabled(commandPlugin: JavaPlugin?, command: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, executor: `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Register a plugin command if it is set to be enabled in its config |
| [reloadCommands](reload-commands.html) | `fun reloadCommands(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Reload commands from a plugin`fun reloadCommands(setToAnythingToGetReturnValue: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Reload commands from a plugin and send a message to the executor |
| [unloadAllCommands](unload-all-commands.html) | `fun unloadAllCommands(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Unloads all current commands |

