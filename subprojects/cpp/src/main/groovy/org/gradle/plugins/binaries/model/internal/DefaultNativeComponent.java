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
package org.gradle.plugins.binaries.model.internal;

import org.gradle.api.DomainObjectSet;
import org.gradle.api.Project;
import org.gradle.api.internal.DefaultDomainObjectSet;
import org.gradle.api.internal.tasks.DefaultTaskDependency;
import org.gradle.api.tasks.TaskDependency;
import org.gradle.internal.os.OperatingSystem;
import org.gradle.plugins.binaries.model.NativeComponent;
import org.gradle.plugins.binaries.model.SourceSet;
import org.gradle.util.GUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultNativeComponent implements NativeComponent {
    private final String name;
    private final DomainObjectSet<SourceSet> sourceSets;
    private final Project project;
    private final DefaultTaskDependency buildDependencies = new DefaultTaskDependency();
    private String baseName;
    private List<Object> compilerArgs = new ArrayList<Object>();

    public DefaultNativeComponent(String name, Project project) {
        this.name = name;
        this.sourceSets = new DefaultDomainObjectSet<SourceSet>(SourceSet.class);
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void builtBy(Object... tasks) {
        buildDependencies.add(tasks);
    }

    public TaskDependency getBuildDependencies() {
        return buildDependencies;
    }

    public DomainObjectSet<SourceSet> getSourceSets() {
        return sourceSets;
    }

    public String getBaseName() {
        return GUtil.elvis(baseName, name);
    }

    public String getOutputFileName() {
        return OperatingSystem.current().getExecutableName(getBaseName());
    }

    public File getOutputFile() {
        return new File(project.getBuildDir(), "binaries/" + getOutputFileName());
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public List<Object> getCompilerArgs() {
        return compilerArgs;
    }

    public void compilerArgs(Object... args) {
        Collections.addAll(this.compilerArgs, args);
    }
}