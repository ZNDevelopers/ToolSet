---
title: ConfigurationTools - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [ConfigurationTools](./index.html)

# ConfigurationTools

`object ConfigurationTools`

Tools to help with reloading configuration and commands

### Functions

| [createToolSetDataFile](create-tool-set-data-file.html) | `fun createToolSetDataFile(plugin: Plugin, resourcePath: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, dataFileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Creates a data file under the plugins/ToolSet folder |
| [setIfNotSet](set-if-not-set.html) | `fun setIfNotSet(section: ConfigurationSection, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): ConfigurationSection`<br>Set a configuration value if it has not yet been set`fun setIfNotSet(section: YamlConfiguration, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): YamlConfiguration`<br>Set a configuration value if it has not yet been set [YamlConfiguration version](#)`fun setIfNotSet(section: `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)`, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)<br>Set a configuration value if it has not yet been set [ToolSetConfiguration version](#) |

