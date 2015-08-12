#!/bin/bash
# RestQChal-JobService
#
# description:

case $1 in
    start)
        /bin/bash /opt/RestQChal/JobService/jobservice-start.sh
    ;;
    stop)
        /bin/bash /opt/RestQChal/JobService/jobservice-stop.sh
    ;;
    restart)
        /bin/bash /opt/RestQChal/JobService/jobservice-start.sh
        /bin/bash /opt/RestQChal/JobService/jobservice-stop.sh
    ;;
esac
exit 0
