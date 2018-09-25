---
title: ToolSetConfiguration - ToolSet
---

[ToolSet](../../index.html) / [com.zndevs.toolset](../index.html) / [ToolSetConfiguration](./index.html)

# ToolSetConfiguration

`class ToolSetConfiguration : YamlConfiguration`

ToolSet Configuration class

### Constructors

| [&lt;init&gt;](-init-.html) | `ToolSetConfiguration(dataDirectory: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)`)`<br>`ToolSetConfiguration(dataDirectory: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)`, fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>`ToolSetConfiguration(premadeConfig: YamlConfiguration, dataDirectory: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)`)`<br>Creates a new instance of the ToolSetConfiguration class |

### Properties

| [fileName](file-name.html) | `var fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?`<br>File name of the configuration |
| [filePath](file-path.html) | `var filePath: `[`File`](http://docs.oracle.com/javase/6/docs/api/java/io/File.html)`?`<br>Gets the data path set by init |

### Functions

| [save](save.html) | `fun save(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Saves the configuration (warning: overrides!) |
| [setDefault](set-default.html) | `fun setDefault(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

