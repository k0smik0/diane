#!/bin/bash

ln -fs ${OLYMPUS_HOME}/Diane/library/task/roboguiced/build/jars/diane-robotask.jar
ln -fs ${OLYMPUS_HOME}/Diane/library/utils/roboguiced/build/jars/diane-roboutils.jar

for i in common roboguiced; do
   ln -fs ${OLYMPUS_HOME}/Diane/demo/test_demo-utils/build/jars/diane_library_-_test_demo-utils--${i}.jar
done
