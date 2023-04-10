/*
 * Copyright 2023 the original author or authors.
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

package org.gradle.testing.junitvintage

import org.gradle.integtests.fixtures.MultiVersionIntegrationSpec
import org.gradle.testing.fixture.AbstractJUnitMultiVersionIntegrationTest

trait JUnitVintageMultiVersionTest {
    AbstractJUnitMultiVersionIntegrationTest.BuildScriptConfiguration getBuildScriptConfiguration() {
        return new JUnitVintageBuildScriptConfiguration()
    }

    static class JUnitVintageBuildScriptConfiguration implements AbstractJUnitMultiVersionIntegrationTest.BuildScriptConfiguration {
        String testFrameworkConfigurationMethod = "useJUnitPlatform()"

        List<String> getTestFrameworkImplementationDependencies() {
            return ["junit:junit:4.13"]
        }

        List<String> getTestFrameworkRuntimeDependencies() {
            return ["org.junit.vintage:junit-vintage-engine:${dependencyVersion}"]
        }

        static String getDependencyVersion() {
            return MultiVersionIntegrationSpec.version.toString().substring("Vintage:".length())
        }

        @Override
        String getIncludeCategoryOrTagConfigurationElement() {
            return "includeTags"
        }

        @Override
        String getExcludeCategoryOrTagConfigurationElement() {
            return "excludeTags"
        }
    }
}
