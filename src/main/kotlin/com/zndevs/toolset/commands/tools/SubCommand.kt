package com.zndevs.toolset.commands.tools

import com.zndevs.toolset.ToolSetCommand

class SubCommand(
        /**
         * Full-length name of the command
         */
        var longName: String,
        /**
         * Shortened names (aliases) of the command
         */
        var shortNames: Array<String>,
        /**
         * A description about the command
         */
        var description: String?,
        /**
         * Command runner instance
         */
        var command: ToolSetCommand) {

    var inlineArgs: String? = null

    /**
     * Creates a subcommand instance without a description
     * @param name Name of the command
     * @param charNames Aliases of the command
     * @param commandRunner The command runner instance
     */
    constructor(name: String, charNames: Array<String>, commandRunner: ToolSetCommand) : this(name, charNames, null, commandRunner) {}

    /**
     * Creates a subcommand instance without aliases
     * @param name Name of the command
     * @param desc The command's description
     * @param commandRunner The command runner instance
     */
    constructor(name: String, desc: String, commandRunner: ToolSetCommand) : this(name, emptyArray(), desc, commandRunner) {}

    /**
     * Creates a subcommand instance without a description or aliases
     * @param name Name of the command
     * @param commandRunner The command runner instance
     */
    constructor(name: String, commandRunner: ToolSetCommand) : this(name, emptyArray(), null, commandRunner) {}

    /**
     * Creates a subcommand instance
     * @param name Name of the command
     * @param charNames Aliases of the command
     * @param customHandledArgs Arguments that are customly handled
     * @param desc The command's description
     * @param commandRunner The command runner lambda
     */
    constructor(name: String, charNames: Array<String>, customHandledArgs: String?, desc: String?, commandRunner: SimpleCommandLambda) : this(name, charNames, desc, SimpleCommand(name, commandRunner)) {
        inlineArgs = customHandledArgs
    }

    /**
     * Creates a subcommand instance without customly handled arguments
     * @param name Name of the command
     * @param charNames Aliases of the command
     * @param desc The command's description
     * @param commandRunner The command runner lambda
     */
    constructor(name: String, charNames: Array<String>, desc: String, commandRunner: SimpleCommandLambda) : this(name, charNames, null, desc, commandRunner) {}

    /**
     * Creates a subcommand instance without customly handled arguments or a description
     * @param name Name of the command
     * @param charNames Aliases of the command
     * @param commandRunner The command runner lambda
     */
    constructor(name: String, charNames: Array<String>, commandRunner: SimpleCommandLambda) : this(name, charNames, null, null, commandRunner) {}

    /**
     * Creates a subcommand instance without customly handled arguments or aliases
     * @param name Name of the command
     * @param desc The command's description
     * @param commandRunner The command runner lambda
     */
    constructor(name: String, desc: String, commandRunner: SimpleCommandLambda) : this(name, emptyArray(), desc, null, commandRunner) {}

    /**
     * Creates a subdommand instance with just a name and command runner
     * @param name Name of the command
     * @param commandRunner The command runner lambda
     */
    constructor(name: String, commandRunner: SimpleCommandLambda) : this(name, emptyArray(), null, null, commandRunner) {}
}
