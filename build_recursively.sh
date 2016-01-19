Diane_Home=$PWD;
function go() {
   for i in $(find $1 | grep build.xml | sed "s/build.xml//g"| sort); do 
      cd $i ; 
      echo -n "$i "
      ant -q >/dev/null 2>&1;
      [ $? -eq 0 ] && echo ok || echo error
      cd $Diane_Home; 
   done
}

go library
go demo
