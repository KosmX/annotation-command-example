package net.fabricmc.example.commands

import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.example.Command
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.command.CommandManager.*
import net.minecraft.text.Text

@Command
class ExampleCommand {
    fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
        dispatcher.register(literal("foo")
            .executes { context ->
                context.source.sendMessage(Text.literal("bar"))
                return@executes 0
            })
    }
}