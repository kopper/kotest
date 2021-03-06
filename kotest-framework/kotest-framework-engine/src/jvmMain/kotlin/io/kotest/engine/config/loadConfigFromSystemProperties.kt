package io.kotest.engine.config

import io.kotest.core.config.ConcurrencyMode
import io.kotest.core.spec.IsolationMode
import io.kotest.core.test.AssertionMode
import io.kotest.core.internal.KotestEngineSystemProperties
import io.kotest.fp.Option
import io.kotest.fp.toOption
import io.kotest.mpp.sysprop

internal fun isolationMode(): Option<IsolationMode> =
   sysprop(KotestEngineSystemProperties.isolationMode).toOption().map { IsolationMode.valueOf(it) }

internal fun assertionMode(): Option<AssertionMode> =
   sysprop(KotestEngineSystemProperties.assertionMode).toOption().map { AssertionMode.valueOf(it) }

internal fun parallelism(): Option<Int> =
   sysprop(KotestEngineSystemProperties.parallelism).toOption().map { it.toInt() }

internal fun timeout(): Option<Long> = sysprop(KotestEngineSystemProperties.timeout).toOption().map { it.toLong() }

internal fun invocationTimeout(): Option<Long> =
   sysprop(KotestEngineSystemProperties.invocationTimeout).toOption().map { it.toLong() }

internal fun allowMultilineTestName(): Option<Boolean> =
   sysprop(KotestEngineSystemProperties.allowMultilineTestName).toOption().map { it.toUpperCase() == "TRUE" }

internal fun concurrencyMode(): Option<ConcurrencyMode> =
   sysprop(KotestEngineSystemProperties.concurrencyMode).toOption().map { ConcurrencyMode.valueOf(it) }

internal fun globalAssertSoftly(): Option<Boolean> =
   sysprop(KotestEngineSystemProperties.globalAssertSoftly).toOption().map { it.toUpperCase() == "TRUE" }

internal fun testNameAppendTags(): Option<Boolean> =
   sysprop(KotestEngineSystemProperties.testNameAppendTags).toOption().map { it.toUpperCase() == "TRUE" }

/**
 * Returns a [DetectedProjectConfig] which is built from system properties where supported.
 */
internal fun loadConfigFromSystemProperties(): DetectedProjectConfig {
   return DetectedProjectConfig(
      isolationMode = isolationMode(),
      assertionMode = assertionMode(),
      parallelism = parallelism(),
      concurrencyMode = concurrencyMode(),
      timeout = timeout(),
      invocationTimeout = invocationTimeout(),
      testNameRemoveWhitespace = allowMultilineTestName(),
      globalAssertSoftly = globalAssertSoftly(),
      testNameAppendTags = testNameAppendTags()
   )
}
