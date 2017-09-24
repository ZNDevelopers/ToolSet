ToolSet
=======
Modularly enhance your Spigot Server
------------------------------------

We're now open source and on GitHub!! Yay!

...

The *Spigot ToolSet Plugin* enables you to interact, control and most
importantly have fun with your users, enhancing your experience in running and
playing on a Spigot server.

Contributing
------------

Want to help us out with ToolSet?

The main thing you can do is make plugins. They are what actually does stuff
and are the most important part of everything. There is some help on plugins in
the wiki.

If you don't want to make plugins, of course you can work on ToolSet too. Just
fork the repo, create whatever you want and submit a pull request. Policy
coming soon.

### Maven repository
It costs too much to host our Maven repo on a proper server so it's currently
hosted @ zndevs.zoweb.me. So, it is slow to download (and upload) files from
it. Want it to be faster? You can't currently donate so if you have your own
Maven repo that we can use that would be cool. We also build our stuff using
TeamCity so if you have a server that can run that, that would be awesome too.

#### What is the URL?

Stick this into the repository URL option in Maven:

`http://zndevs.zoweb.me/repository/repository`

Then you can use ToolSet's API!

Wiki
----

There is a wiki if you need some help in ToolSet, just click the Wiki button on
the toolbar. It contains a bunch of information about creating ToolSet modules.
If you want to learn how to use ToolSet, it also [will soon] have info about
doing that too.

To Do List
----------

- MAIN SYSTEM<br/>
  `[=======>--]` 80% complete: Modularity and updater
  - Ability to be modular - **under development**
  - Auto-Updater - **under development**
  - Commands - **complete**

- MODULARITY SYSTEM<br/>
  `[========>-]` 90% complete: New site
  - Modular configuration files - **complete**
  - <del>Modular .jar files - **complete**</del>
  - Updating to work with new unreleased site - **under development**
  - Bug fixes, smoothing and testing - *waiting*

- JSONTP COMMAND<br/>
  `[----------]` 0% complete: Waiting
  - Teleport a player to a location - *waiting*
  - Teleport a player to another player - *waiting*
  - Teleport a player to an entity - *waiting*
  - Teleport an entity to a player - *waiting*
  

- HOME COMMAND<br/>
  `[========>-]` 90% complete: Configuration
  - Create home - **complete**
  - Teleport to home - **complete**
  - List homes - **complete**
  - Help menu for module - **complete**
  - Config files and yml files for storage - **complete**
  - Remove homes - **complete**
  - Customisable options (eg timer) *next on list*


- WARP COMMAND<br/>
  `[=========>]` 100% complete
  - Create warp - **complete**
  - List warps (in world) - **complete**
  - Remove warps - **complete**
  - Teleport to warps - **complete**