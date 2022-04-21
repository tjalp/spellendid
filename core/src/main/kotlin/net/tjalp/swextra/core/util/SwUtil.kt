package net.tjalp.swextra.core.util

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

val EXECUTOR_SERVICE: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()