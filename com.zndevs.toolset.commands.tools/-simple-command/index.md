---
title: SimpleCommand - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.commands.tools](../index.html) / [SimpleCommand](./index.html)

# SimpleCommand

`class SimpleCommand : `[`ToolSetCommand`](../../com.zndevs.toolset/-tool-set-command/index.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `SimpleCommand(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`)`<br>`SimpleCommand(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandRunner: `[`SimpleCommandLambda`](../-simple-command-lambda.html)`, commandPermission: Permission)` |

### Inherited Properties

| [enabled](../../com.zndevs.toolset/-tool-set-command/enabled.html) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [getCommandName](get-command-name.html) | `fun getCommandName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Gets the name of the command |
| [getRequiredPermission](get-required-permission.html) | `fun getRequiredPermission(subCommand: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Permission`<br>Gets the permission required to run the specified subcommand |
| [getSubCommands](get-sub-commands.html) | `fun getSubCommands(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`SubCommand`](../-sub-command/index.html)`>`<br>***IMPORTANT**: Do not recreate subcommands when this is run!* |
| [onCommand](on-command.html) | `fun onCommand(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setConfigurationDefaults](set-configuration-defaults.html) | `fun setConfigurationDefaults(defaults: `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)`): `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)<br>Sets the default configuration values for this command |

### Inherited Functions

| [execute](../../com.zndevs.toolset/-tool-set-command/execute.html) | `fun execute(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onTabComplete](../../com.zndevs.toolset/-tool-set-command/on-tab-complete.html) | `open fun onTabComplete(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [run](../../com.zndevs.toolset/-tool-set-command/run.html) | `fun run(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns a command's subcommands. Note: run last in your command executor! |
| [setPlugin](../../com.zndevs.toolset/-tool-set-command/set-plugin.html) | `open fun setPlugin(plugin: JavaPlugin): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the plugin using this command. |

