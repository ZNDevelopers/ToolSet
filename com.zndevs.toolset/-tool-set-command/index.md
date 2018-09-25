---
title: ToolSetCommand - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset](../index.html) / [ToolSetCommand](./index.html)

# ToolSetCommand

`abstract class ToolSetCommand : CommandExecutor, Listener, TabCompleter`

### Constructors

| [&lt;init&gt;](-init-.html) | `ToolSetCommand()` |

### Properties

| [enabled](enabled.html) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [execute](execute.html) | `fun execute(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getCommandName](get-command-name.html) | `abstract fun getCommandName(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Gets the name of the command |
| [getRequiredPermission](get-required-permission.html) | `open fun getRequiredPermission(subCommand: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Permission`<br>Gets the permission required to run the specified subcommand |
| [getSubCommands](get-sub-commands.html) | `abstract fun getSubCommands(): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`SubCommand`](../../com.zndevs.toolset.commands.tools/-sub-command/index.html)`>`<br>***IMPORTANT**: Do not recreate subcommands when this is run!* |
| [onCommand](on-command.html) | `open fun onCommand(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onTabComplete](on-tab-complete.html) | `open fun onTabComplete(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [run](run.html) | `fun run(sender: CommandSender, command: Command, label: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns a command's subcommands. Note: run last in your command executor! |
| [setConfigurationDefaults](set-configuration-defaults.html) | `abstract fun setConfigurationDefaults(defaults: `[`ToolSetConfiguration`](../-tool-set-configuration/index.html)`): `[`ToolSetConfiguration`](../-tool-set-configuration/index.html)<br>Sets the default configuration values for this command |
| [setPlugin](set-plugin.html) | `open fun setPlugin(plugin: JavaPlugin): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the plugin using this command. |

### Inheritors

| [SimpleCommand](../../com.zndevs.toolset.commands.tools/-simple-command/index.html) | `class SimpleCommand : `[`ToolSetCommand`](./index.html) |
| [ToolSetCommandCommand](../../com.zndevs.toolset.commands/-tool-set-command-command/index.html) | `class ToolSetCommandCommand : `[`ToolSetCommand`](./index.html) |
| [ToolSetMainCommand](../../com.zndevs.toolset.commands/-tool-set-main-command/index.html) | `class ToolSetMainCommand : `[`ToolSetCommand`](./index.html) |
| [ToolSetUpdateCommand](../../com.zndevs.toolset.commands/-tool-set-update-command/index.html) | `class ToolSetUpdateCommand : `[`ToolSetCommand`](./index.html) |

