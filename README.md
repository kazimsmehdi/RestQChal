# RestQChal

Its an a solution that uses DropWizard to host REST service with WebSocket connection to give realtime updated of the data comming to the service. That data is then pushed to Redis Queue , that then popped up by workers that after processing submit the results on MongoDB
