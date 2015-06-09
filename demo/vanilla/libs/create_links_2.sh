#!/bin/bash

[ -z $OLYMPUS_PROJECTS ] && echo OLYMPUS_PROJECTS not setted && exit 1

for i in task utils; do
   ln -fs ${OLYMPUS_PROJECTS}/Diane/library/${i}/vanilla/build/jars/diane-${i}.jar
done

for i in common vanilla; do
   ln -fs ${OLYMPUS_PROJECTS}/Diane/demo/test_demo-utils/build/jars/diane_library_-_test_demo-utils--${i}.jar
done


