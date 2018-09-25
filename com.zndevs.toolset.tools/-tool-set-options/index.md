---
title: ToolSetOptions - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [ToolSetOptions](./index.html)

# ToolSetOptions

`object ToolSetOptions`

Tools to help with configuration files and other general options

### Properties

| [dataDirectory](data-directory.html) | `val dataDirectory: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)<br>Get the directory that data is saved in |
| [errorPrefix](error-prefix.html) | `const val errorPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ToolSet error prefix |
| [helpPrefix](help-prefix.html) | `const val helpPrefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [noPermissionsMessage](no-permissions-message.html) | `val noPermissionsMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ToolSet No-Perms message |
| [plugin](plugin.html) | `var plugin: JavaPlugin?`<br>***IMPORTANT:** DO NOT CHANGE* |
| [prefix](prefix.html) | `const val prefix: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ToolSet prefix |
| [toolSetApiPath](tool-set-api-path.html) | `var toolSetApiPath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>ToolSet API URL. If you have a local copy of the ToolSet website for debugging, set the TOOLSET_API environment variable. |

### Functions

| [getCommandOptions](get-command-options.html) | `fun ~~getCommandOptions~~(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): ConfigurationSection`<br>Get the options for a command from config.yml |
| [getDataFile](get-data-file.html) | `fun getDataFile(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)<br>Get the File for a command config file |
| [getDataOptions](get-data-options.html) | `fun getDataOptions(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): YamlConfiguration`<br>Get command options from the command file |
| [getHelpFormat](get-help-format.html) | `fun getHelpFormat(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandOptions: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Get help text from command`fun getHelpFormat(commandName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, commandOptions: `[`Iterable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)`<`[`Node`](../-node/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>>): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>Get help text from a command using Nodes |
| [getOptions](get-options.html) | `fun getOptions(file: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)`): YamlConfiguration`<br>Get YamlConfiguration from a File |
| [getToolSetApiPath](get-tool-set-api-path.html) | `fun getToolSetApiPath(file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Get public API url |
| [isCommandEnabled](is-command-enabled.html) | `fun isCommandEnabled(filePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Check if a command is enabled from its config file |

