# maven打包
mvn clean install package

# 结束原有进程
processname='neptune'
PROCESS=`ps -ef|grep $processname|grep -v grep|grep -v PPID|awk '{ print $2}'`
for i in $PROCESS
   do
   echo "Kill the $1 process [ $i ]"
   kill -9 $i
 done

# 检查日志目录是否存在
if [ ! -d /tmp/log ];then
  mkdir /tmp/log
fi

# 启动服务
nohup java -jar neptune-server-starter/target/neptune-server-starter-0.0.1-SNAPSHOT.jar  > /tmp/log/starter.out  2>&1 &
nohup java -jar neptune-admin-starter/target/neptune-admin-starter-0.0.1-SNAPSHOT.jar > /tmp/log/admin.out 2>&1 &
nohup java -jar neptune-web-starter/target/neptune-web-starter-0.0.1-SNAPSHOT.jar  > /tmp/log/web.out 2>&1 &
exit 0
