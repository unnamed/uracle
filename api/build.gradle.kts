plugins {
    id("creative.publishing-conventions")
}

description = "A resource-pack library for Minecraft: Java Edition."

dependencies {
    compileOnlyApi("org.jetbrains:annotations:26.0.1")
    api("net.kyori:adventure-key:4.19.0")
    api("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.14.0")
}