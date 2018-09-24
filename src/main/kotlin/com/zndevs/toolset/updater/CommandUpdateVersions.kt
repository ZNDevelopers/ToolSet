package com.zndevs.toolset.updater

/**
 * Stores module versions, a name, and some data
 */

class CommandUpdateVersions {

    var commandName = ""
        internal set
    var oldVersion = ""
        internal set
    var newVersion = ""
        internal set
    var otherData: Any? = null
        internal set

    constructor()

    constructor(name: String, oldVer: String, newVer: String) {
        commandName = name
        oldVersion = oldVer
        newVersion = newVer
    }

    constructor(name: String, oldVer: String, newVer: String, otherDta: Any) {
        commandName = name
        oldVersion = oldVer
        newVersion = newVer
        otherData = otherDta
    }

}
