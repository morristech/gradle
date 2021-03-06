/*
 * Copyright 2011 the original author or authors.
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
package org.gradle.plugins.binaries.model;

import org.gradle.api.Buildable;
import org.gradle.api.DomainObjectSet;
import org.gradle.api.Named;

import java.io.File;
import java.util.List;

/**
 * Something to be created.
 */
public interface NativeComponent extends Named, Buildable {

    DomainObjectSet<SourceSet> getSourceSets();

    String getBaseName();

    String getOutputFileName();

    File getOutputFile();

    void setBaseName(String baseName);

    List<Object> getCompilerArgs();

    void compilerArgs(Object... args);

    // TODO:DAZ This should be on NativeBinary together with Buildable (if required at all)
    void builtBy(Object... tasks);
}
