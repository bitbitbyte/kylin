/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.apache.kylin.engine.flink;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.TaskManagerOptions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A util class which build mapping between Flink config option keys from flink-conf.yaml
 * and Flink on YARN cmd option.
 */
public class FlinkOnYarnConfigMapping {

    public static final Map<String, String> flinkOnYarnConfigMap;

    static {
        flinkOnYarnConfigMap = new HashMap<>();

        //mapping job manager heap size -> -yjm
        ConfigOption<String> jmHeapSizeOption = JobManagerOptions.JOB_MANAGER_HEAP_MEMORY;
        flinkOnYarnConfigMap.put(jmHeapSizeOption.key(), "-yjm");
        if (jmHeapSizeOption.hasDeprecatedKeys()) {
            Iterator<String> deprecatedKeyIterator = jmHeapSizeOption.deprecatedKeys().iterator();
            while (deprecatedKeyIterator.hasNext()) {
                flinkOnYarnConfigMap.put(deprecatedKeyIterator.next(), "-yjm");
            }
        }

        //mapping task manager heap size -> -ytm
        ConfigOption<String> tmHeapSizeOption = TaskManagerOptions.MANAGED_MEMORY_SIZE;
        flinkOnYarnConfigMap.put(tmHeapSizeOption.key(), "-ytm");
        if (tmHeapSizeOption.hasDeprecatedKeys()) {
            Iterator<String> deprecatedKeyIterator = tmHeapSizeOption.deprecatedKeys().iterator();
            while (deprecatedKeyIterator.hasNext()) {
                flinkOnYarnConfigMap.put(deprecatedKeyIterator.next(), "-ytm");
            }
        }

        ConfigOption<Integer> taskSlotNumOption = TaskManagerOptions.NUM_TASK_SLOTS;
        flinkOnYarnConfigMap.put(taskSlotNumOption.key(), "-ys");
        if (taskSlotNumOption.hasDeprecatedKeys()) {
            Iterator<String> deprecatedKeyIterator = taskSlotNumOption.deprecatedKeys().iterator();
            while (deprecatedKeyIterator.hasNext()) {
                flinkOnYarnConfigMap.put(deprecatedKeyIterator.next(), "-ys");
            }
        }
    }

}
