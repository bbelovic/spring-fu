/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.kofu.web

import org.springframework.boot.autoconfigure.mustache.MustacheInitializer
import org.springframework.boot.autoconfigure.mustache.MustacheProperties
import org.springframework.boot.autoconfigure.mustache.MustacheReactiveWebInitializer
import org.springframework.boot.kofu.AbstractModule
import org.springframework.context.support.GenericApplicationContext

/**
 * @author Sebastien Deleuze
 */
internal class MustacheModule(
	private val properties: MustacheProperties
) : AbstractModule() {

	override fun registerBeans(context: GenericApplicationContext) {
		MustacheInitializer(properties).initialize(context)
		MustacheReactiveWebInitializer(properties).initialize(context)
	}
}

fun WebFluxServerModule.mustache(
	prefix: String = "classpath:/templates/",
	suffix: String = ".mustache") {
	val properties = MustacheProperties()
	properties.prefix = prefix
	properties.suffix = suffix
	initializers.add(MustacheModule(properties))
}