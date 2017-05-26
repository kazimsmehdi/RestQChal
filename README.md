# RestQChal

Is is a Job Processing Solution that uses DropWizard to host REST service with WebSocket connection to give realtime updated of the data (JOB) comming to the service.  Received Job will then push to Redis Queue, then that job can be pulled by running worker clients to perform the job, after processing worker will submit the results on MongoDB

This solution consist of 3 project.
- JobService
This one contains DropWizard + Websocket that push the jobs to Redis Queue
 - Execution
  update JobService/config/job-service.yml to the Redis settigs
  - java -jar restqchal-job-service-1.0-SNAPSHOT-shaded.jar server job-service.yml
- JobWorker
That subscribe to the Redis Queue and process and save the object to MongoDB
 - Execution
  update JobWorker/src/config/job-worker.yml to the Redis  & Mongo settings
  - java -jar restqchal-job-worker-1.0-SNAPSHOT-shaded.jar job-worker.yml
- QueueViewer
Html App to get Realtime updates Whats comming on REST service
 - Execution
  - update QueueViewer/js/app.js JobService IP


