#!/bin/bash
# RestQChal-JobWorker
#
# description:

case $1 in
    start)
        /bin/bash /opt/RestQChal/JobWorker/jobworker-start.sh
    ;;
    stop)
        /bin/bash /opt/RestQChal/JobWorker/jobworker-stop.sh
    ;;
    restart)
        /bin/bash /opt/RestQChal/JobWorker/jobworker-stop.sh
        /bin/bash /opt/RestQChal/JobWorker/jobworker-start.sh
    ;;
esac
exit 0
