package net.fabricmc.example


@Retention(AnnotationRetention.RUNTIME) // Runtime for runtime discovery
@Target(AnnotationTarget.CLASS) // Classes can be annotated with it
annotation class Command
