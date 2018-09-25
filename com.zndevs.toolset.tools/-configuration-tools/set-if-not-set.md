---
title: ConfigurationTools.setIfNotSet - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset.tools](../index.html) / [ConfigurationTools](index.html) / [setIfNotSet](./set-if-not-set.html)

# setIfNotSet

`fun setIfNotSet(section: ConfigurationSection, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): ConfigurationSection`

Set a configuration value if it has not yet been set

### Parameters

`section` - The configuration section containing the value

`path` - The path to the value

`value` - The value to set the path to

**Return**
The configuration section containing the value

`fun setIfNotSet(section: YamlConfiguration, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): YamlConfiguration`

Set a configuration value if it has not yet been set [YamlConfiguration version](#)

### Parameters

`section` - The configuration section containing the value

`path` - The path to the value

`value` - The value to set the path to

**Return**
The configuration section containing the value

`fun setIfNotSet(section: `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)`, path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`ToolSetConfiguration`](../../com.zndevs.toolset/-tool-set-configuration/index.html)

Set a configuration value if it has not yet been set [ToolSetConfiguration version](#)

### Parameters

`section` - The configuration section containing the value

`path` - The path to the value

`value` - The value to set the path to

**Return**
The configuration section containing the value

