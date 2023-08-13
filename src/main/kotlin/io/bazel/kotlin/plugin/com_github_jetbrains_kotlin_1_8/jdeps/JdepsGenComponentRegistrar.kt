@file:Suppress("ktlint:standard:package-name")

package io.bazel.kotlin.plugin.com_github_jetbrains_kotlin_1_8.jdeps

import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.extensions.StorageComponentContainerContributor
import org.jetbrains.kotlin.resolve.jvm.extensions.AnalysisHandlerExtension

@OptIn(org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi::class)
class JdepsGenComponentRegistrar : CompilerPluginRegistrar() {

  override val supportsK2: Boolean = false
  override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
    // Capture all types referenced by the compiler for this module and look up the jar from which
    // they were loaded from
    val extension = JdepsGenExtension(configuration)
    AnalysisHandlerExtension.registerExtension(extension)
    StorageComponentContainerContributor.registerExtension(extension)
  }
}
