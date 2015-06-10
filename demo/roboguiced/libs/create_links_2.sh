#!/bin/bash

[ -z $OLYMPUS_PROJECTS ] && echo OLYMPUS_PROJECTS not setted && exit 1

ln -fs ${OLYMPUS_PROJECTS}/Diane/library/task/roboguiced/build/jars/diane-robotasks.jar
ln -fs ${OLYMPUS_PROJECTS}/Diane/library/utils/roboguiced/build/jars/diane-roboutils.jar

for i in common roboguiced; do
   ln -fs ${OLYMPUS_PROJECTS}/Diane/demo/test_demo-utils/build/jars/diane_library_-_test_demo-utils--${i}.jar
done
