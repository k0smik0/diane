#!/bin/bash

for i in task utils; do
   ln -fs ${OLYMPUS_HOME}/Diane/library/${i}/vanilla/build/jars/diane-${i}.jar
done

for i in common vanilla; do
   ln -fs ${OLYMPUS_HOME}/Diane/demo/test_demo-utils/build/jars/diane_library_-_test_demo-utils--${i}.jar
done


