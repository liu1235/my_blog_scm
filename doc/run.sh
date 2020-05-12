#!/bin/bash
#
# chkconfig:   - 20 80
# description: Starts and stops the App.
# author:scloud

RUNNING_USER=root
ADATE=`date +%Y%m%d%H%M%S`
APP_NAME=blog-service

APP_HOME=`pwd`
dirname $0|grep "^/" >/dev/null
if [ $? -eq 0 ];then
   APP_HOME=`dirname $0`
else
    dirname $0|grep "^\." >/dev/null
    retval=$?
    if [ $retval -eq 0 ];then
        APP_HOME=`dirname $0|sed "s#^.#$APP_HOME#"`
    else
        APP_HOME=`dirname $0|sed "s#^#$APP_HOME/#"`
    fi
fi

if [ ! -d "$APP_HOME/logs" ];then
  mkdir $APP_HOME/logs
fi

LOG_PATH=$APP_HOME/logs/$APP_NAME.out
GC_LOG_PATH=$APP_HOME/logs/gc-$APP_NAME-$ADATE.log
#JMX监控需用到
#JMX="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1091 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"
#JVM参数
JVM_OPTS="-Dname=$APP_NAME -Dspring.profiles.active=prod -Dloader.path="$APP_HOME/lib/" -Duser.timezone=Asia/Shanghai -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDateStamps -Xloggc:$GC_LOG_PATH -XX:+PrintGCDetails -XX:NewRatio=1 -XX:SurvivorRatio=30 -XX:+UseParallelGC -XX:+UseParallelOldGC"

JAR_FILE=$APP_HOME/$APP_NAME.jar
pid=0

start(){
  #find $APP_HOME/logs/ -mtime +7 -name "*.log" -exec rm -rf {} \;
  checkpid
  if [ ! -n "$pid" ]; then
    JAVA_CMD="nohup java -jar $JVM_OPTS $JAR_FILE  &"
    echo $JAVA_CMD
    su - $RUNNING_USER -c "$JAVA_CMD"
    echo "---------------------------------"
    echo "启动完成，按CTRL+C退出日志界面即可>>>>>"
    echo "---------------------------------"
    sleep 2s
    tail -f /root/logs/blog-server/info/log_info.log
  else
      echo "$APP_NAME is runing PID: $pid"   
  fi

}


checkpid(){
    pid=`ps -ef |grep $JAR_FILE |grep -v grep |awk '{print $2}'`
}

stop(){
    checkpid
    if [ ! -n "$pid" ]; then
     echo "$APP_NAME not runing"
    else
      echo "$APP_NAME stop..."
      kill -9 $pid
    fi 
}

restart(){
    stop 
    sleep 1s
    start
}

case $1 in  
          start) start;;  
          stop)  stop;;
          restart)  restart;;  

esac 
