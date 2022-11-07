package net.fabricmc.example

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import org.reflections.Reflections
import org.slf4j.Logger
import org.slf4j.LoggerFactory

// This logger is used to write text to the console and the log file.
// It is considered best practice to use your mod id as the logger's name.
// That way, it's clear which mod wrote info, warnings, and errors.
@JvmField
val LOGGER: Logger = LoggerFactory.getLogger("modid")

@Suppress("unused")
fun onInitialize() {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    LOGGER.info("Hello Fabric world!")

    //Subscribe to event, pass lambda
    CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->

        Reflections("net.fabricmc.example.commands") // Reflect to package
            .getTypesAnnotatedWith(Command::class.java) // Get classes annotated with Command
            .map { it.getConstructor().newInstance() } // Construct those classes
            .forEach {
                // Invoke the register member on those objects
                it.javaClass.getMethod("register", CommandDispatcher::class.java).invoke(it, dispatcher)
            }
    }
}
