#!/bin/bash

dir=`dirname $0`
cd $dir
dir=`pwd`
log_file=/var/log/hxjy/hxjy.log
jarfile="`basename $dir`.jar"
#jarfile=`ls $dir|grep "service.jar"`
echo $jarfile

pid=`jps -l|grep hxjy-1.0-SNAPSHOT|grep $jarfile|awk '{print $1}'`
if [ $pid ]
then
        echo "结束$jarfile进程$pid"
        kill $pid
fi
sleep 1
pid=`jps -l|grep hxjy-1.0-SNAPSHOT|grep $jarfile|awk '{print $1}'`
if [ $pid ]
then
        echo "强制结束$jarfile进程$pid"
        kill -9 $pid
fi

echo "启动$jarfile"
nohup java -Xms512m -Xmx2048m -XX:PermSize=512M -XX:MaxNewSize=2048m -XX:MaxPermSize=1024m -server -jar $dir/$jarfile  > $log_file 2>&1 &
echo $! > $dir/jar.pid
#sleep 2
pid=`jps -l|grep hxjy-1.0-SNAPSHOT|grep $jarfile|awk '{print $1}'`
if [ "$pid" = `cat $dir/jar.pid` ]
then
        echo "进程$!已启动，日志文件$log_file。"
else
        echo "启动失败，查看日志$log_file。"
        echo "" > $dir/jar.pid
fi
