processname='neptune'
PROCESS=`ps -ef|grep $processname|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
   do
   echo "Kill the $1 process [ $i ]"
   kill -9 $i
 done

mvn clean install package
nohup java -jar neptune-server-starter/target/neptune-server-starter-0.0.1-SNAPSHOT.jar & >> /log/starter.out
nohup java -jar neptune-admin-starter/target/neptune-admin-starter-0.0.1-SNAPSHOT.jar & >> /log/admin.out
nohup java -jar neptune-web-starter/target/neptune-web-starter-0.0.1-SNAPSHOT.jar & >> /log/web.out
exit 0
