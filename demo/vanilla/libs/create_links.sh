#!/bin/bash

( [ -z ${OLYMPUS_HOME} ] || [ ! -d ${OLYMPUS_HOME} ] || [ ! -f jars.txt ] ) && echo "OLYMPUS_HOME environment variable is not setted with right directory" && exit 1

jars=$(cat jars.txt)

rm -f *.jar

for jar in ${jars}; do
   ln -fs ${OLYMPUS_HOME}/_libs/${jar} .
done

for i in task utils; do
   ln -fs ${OLYMPUS_HOME}/Diane/library/${i}/vanilla/build/jars/diane-${i}.jar
done

for i in common vanilla; do
   ln -fs ${OLYMPUS_HOME}/Diane/demo/test_demo-utils/build/jars/diane_library_-_test_demo-utils--${i}.jar
done

